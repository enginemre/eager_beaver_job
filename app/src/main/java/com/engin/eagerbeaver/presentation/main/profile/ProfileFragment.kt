package com.engin.eagerbeaver.presentation.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentLoginBinding
import com.engin.eagerbeaver.databinding.FragmentProfileBinding
import com.engin.eagerbeaver.presentation.auth.AuthActivity
import com.engin.eagerbeaver.presentation.main.profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProfileFragment : Fragment(),MenuProvider {

    private var _binding:FragmentProfileBinding? = null
    val binding get() = _binding!!

    private val viewModel:ProfileViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setMenu()
        observeData()
    }

    private fun init(){
        binding.editProfile.setOnClickListener {
            viewModel.editProfile()
        }
    }
    private fun observeData(){
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{currentState->
                if(currentState.isLoading){
                    LoadingDialog.showLoading(requireContext(),false)
                    return@collect
                }else{
                    LoadingDialog.hideLoading()
                }
                currentState.errorMessage?.let {
                    CustomSnackBar.make(
                        requireContext(),
                        view = requireView(),
                        it.asString(requireContext()),
                        type = SnackType.ERROR
                    ).show()
                    viewModel.messageShown()
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
                currentState.route?.let{route->
                    when(route){
                        is Route.Login -> {
                            startActivity(Intent(requireActivity(), AuthActivity::class.java))
                            requireActivity().overridePendingTransition(
                                R.anim.slide_in_right,
                                R.anim.slide_out_right
                            )
                            requireActivity().finish()
                        }else -> {}
                    }
                    viewModel.navigated()
                }
            }
        }
    }

    private fun setMenu(){
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.profile_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
       return when(menuItem.itemId){
            R.id.logout_profile -> {
                viewModel.logout()
                true
            }
            else->{
                false
            }
        }
    }

}