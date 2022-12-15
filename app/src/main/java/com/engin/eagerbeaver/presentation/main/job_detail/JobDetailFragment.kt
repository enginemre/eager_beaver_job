package com.engin.eagerbeaver.presentation.main.job_detail

import android.os.Bundle
import android.view.*
import androidx.activity.R
import androidx.activity.addCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentJobDetailBinding
import com.engin.eagerbeaver.presentation.main.job_detail.viewmodel.JobDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JobDetailFragment : Fragment(), MenuProvider {

    private var  _binding: FragmentJobDetailBinding? = null
    private val binding get() =  _binding!!
    private val viewModel:JobDetailViewModel by viewModels()
    private lateinit var comingFrom: Route?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            comingFrom = if( it.getString("comingFrom") == "/home" && it.getString("comingFrom") != null) Route.Home() else Route.Jobs()
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            goBack(comingFrom!!)
        }
    }

    private fun goBack(comingFrom:Route) {
        if(comingFrom is Route.Jobs){
            val action = JobDetailFragmentDirections.actionJobDetailFragmentSearchToSearchFragment(0L)
            findNavController().navigate(action)
        }else{
            val action = JobDetailFragmentDirections.actionJobDetailFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observe()
        setMenu()
    }

    private fun setMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun init(){
    }

    private fun observe(){
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{currentState ->
                if(currentState.isLoading){
                    LoadingDialog.showLoading(requireContext(),false)
                    return@collect
                }else{
                    LoadingDialog.hideLoading()
                }
                currentState.jobDetail?.let {
                    binding.errorLayoutJobDetail.visibility = View.GONE
                    binding.mainLayoutJobDetail.visibility = View.VISIBLE
                    binding.job = it
                    binding.listener = viewModel
                }
                currentState.errorMessage?.let {
                    binding.errorLayoutJobDetail.visibility = View.VISIBLE
                    binding.mainLayoutJobDetail.visibility = View.GONE
                    binding.listener = viewModel
                    binding.notFoundTVApproved.text = it.asString(requireContext())
                }
                currentState.warningMessage?.let {
                    CustomSnackBar.make(
                        requireContext(),
                        view = requireView(),
                        it.asString(requireContext()),
                        type = SnackType.WARNING
                    ).show()
                    viewModel.messageShown()
                }

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                goBack()
                true
            }
            else -> false
        }
    }


}