package com.engin.eagerbeaver.presentation.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialSetup()
        observeState()
    }

    private fun initialSetup() {
        binding.registerButton.setOnClickListener {
            val checkedRadioButtonId = binding.gravityRadioGroup.checkedRadioButtonId()
            if (checkedRadioButtonId != -1) {
                viewModel.registerUserValid(
                    name = binding.nameRegister.text.toString(),
                    password = binding.passwordRegister.text.toString(),
                    email = binding.emailRegister.text.toString(),
                    userName = binding.usernameRegister.text.toString(),
                    type = if(binding.employerType.id == checkedRadioButtonId) UserRole.EMPLOYER else UserRole.APPLICANT
                )
            } else {
                CustomSnackBar.make(
                    requireContext(),
                    requireView(),
                    getString(R.string.error_radio_button),
                    SnackType.ERROR
                ).show()
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { currentState ->
                if (currentState.isLoading) {
                    LoadingDialog.showLoading(requireContext(), false)
                    return@collect
                } else {
                    LoadingDialog.hideLoading()
                }
                currentState.errorMessage?.let {
                    CustomSnackBar.make(
                        requireContext(),
                        requireView(),
                        it.asString(requireContext()),
                        SnackType.ERROR,
                    ).show()
                    viewModel.messageShown()
                }
                if (currentState.nextPage) {
                    gotoNexPage()
                }
            }
        }
    }

    private fun gotoNexPage() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToHomeGraph()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}