package com.engin.eagerbeaver.presentation.main.my_adverts

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.presentation.adapter.JobAdvertAdapter
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentAdvertsBinding
import com.engin.eagerbeaver.presentation.auth.AuthActivity
import com.engin.eagerbeaver.presentation.main.MainActivity
import com.engin.eagerbeaver.presentation.main.my_adverts.viewmodel.MyAdvertViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class   AdvertsFragment : Fragment(),MenuProvider {

    private var _binding:FragmentAdvertsBinding? = null
    private val binding get() =_binding!!
    private lateinit var jobAdvertAdapter: JobAdvertAdapter
    private lateinit var navController:NavController


    private val viewModel:MyAdvertViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvertsBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setMenu()
        init()
        observeData()
    }

    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{currentState->
                if(currentState.isLoading){
                    LoadingDialog.showLoading(requireContext(),false)
                    return@collect
                }
                else
                    LoadingDialog.hideLoading()
                currentState.errorMessage?.let {
                    CustomSnackBar.make(
                        context = requireContext(),
                        view = requireView(),
                        text = it.asString(requireContext()),
                        type = SnackType.ERROR
                    ).show()
                    viewModel.messageShown()
                }
                currentState.warningMessage?.let {
                    CustomSnackBar.make(
                        context = requireContext(),
                        view = requireView(),
                        text = it.asString(requireContext()),
                        type = SnackType.WARNING
                    ).show()
                    viewModel.messageShown()
                }
                if(currentState.myAdvertLists.isNotEmpty()){
                    jobAdvertAdapter.updateData(currentState.myAdvertLists)
                }
                currentState.route?.let { route->
                    when(route){
                        is Route.AdvertEdit -> {
                            goDetail(route.advert_id)
                        }
                        is Route.Login -> {
                            startActivity(Intent(requireActivity(),AuthActivity::class.java))
                            requireActivity().overridePendingTransition(
                                R.anim.slide_in_right,
                                R.anim.slide_out_right
                            )
                            requireActivity().finish()
                        }
                        else -> {}
                    }
                }


            }
        }
    }

    private fun init(){
        binding.myAdvertsRv.layoutManager = LinearLayoutManager(requireContext())
        jobAdvertAdapter =  JobAdvertAdapter(emptyList(),viewModel)
        binding.myAdvertsRv.adapter = jobAdvertAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.advert_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.add_advert -> {
                goDetail()
                true
            }
            R.id.logout -> {
                viewModel.logout()
                true
            }
            else -> false
        }
    }

    private fun goDetail(advertId:Long? = null){
        val action = AdvertsFragmentDirections.actionAdvertsFragmentToAdvertDetailFragment(advertId = advertId ?: 0L)
        findNavController().navigate(action)
        viewModel.navigated()
    }

}