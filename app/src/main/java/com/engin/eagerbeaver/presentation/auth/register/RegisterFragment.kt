package com.engin.eagerbeaver.presentation.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.domain.model.GoogleUserInfo
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.AndroidEntryPoint
import xyz.teamgravity.imageradiobutton.GravityRadioGroup

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()
    private var account:GoogleUserInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            val args = RegisterFragmentArgs.fromBundle(it)
            account =  args.infoGoogle
        }
        initialSetup()
        observeState()
    }

    private fun initialSetup() {
        account?.let { userInfo->
            binding.emailRegister.setText(userInfo.email)
            binding.nameRegister.setText(userInfo.name)
        }
        binding.gravityRadioGroup.setOnCheckedChangeListener { _, _, _, checkedId ->
            if (checkedId == R.id.applicant_type) {
                binding.applicantLayout.visibility = View.VISIBLE
            } else {
                binding.applicantLayout.visibility = View.GONE
            }
        }

        binding.registerButton.setOnClickListener {
            val checkedRadioButtonId = binding.gravityRadioGroup.checkedRadioButtonId()
            if (checkedRadioButtonId != -1) {
                viewModel.registerUserValid(
                    name = binding.nameRegister.text.toString(),
                    password = binding.passwordRegister.text.toString(),
                    email = binding.emailRegister.text.toString(),
                    userName = binding.usernameRegister.text.toString(),
                    type = if(binding.employerType.id == checkedRadioButtonId) UserRole.EMPLOYEE else UserRole.APPLICANT,
                    interestId = listOf(1,2,3),
                    description = binding.descriptionRegister.text.toString(),
                    title = binding.titleRegister.text.toString()
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
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}