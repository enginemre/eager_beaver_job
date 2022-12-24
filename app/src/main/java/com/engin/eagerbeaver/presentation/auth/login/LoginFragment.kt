package com.engin.eagerbeaver.presentation.auth.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.domain.model.GoogleUserInfo
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentLoginBinding
import com.engin.eagerbeaver.presentation.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var authActivityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(requireActivity(), gso)
        authActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        task.getResult(ApiException::class.java)
                        val account = task.result
                        account.email?.let {
                            viewModel.checkEmailExist(it)
                        }
                        // the email exist

                    } catch (ex: Exception) {
                        CustomSnackBar.make(
                            requireContext(),
                            requireView(),
                            ex.message.toString(),
                            SnackType.ERROR
                        ).show()
                    }
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.isLogin()) {
            goNextScreen(Route.Home())
        }
        binding.loginButton.setOnClickListener {
            viewModel.login(
                binding.userTextInput.text.toString(),
                binding.passwordTextInput.text.toString()
            )
        }
        binding.loginWithGuest.setOnClickListener {
            viewModel.loginAsGuest()
        }
        binding.signInButton.setOnClickListener {
            signInGoogle()
        }
        binding.registerNow.setOnClickListener {
            goNextScreen(Route.Register())
        }
        observeData()
    }

    private fun signInGoogle() {
        val signInIntent: Intent = gsc.signInIntent
        authActivityResultLauncher.launch(signInIntent)
    }

    private fun observeData() {
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
                        SnackType.ERROR
                    ).show()
                    viewModel.messageShown()
                }
                currentState.navigateNext?.let { route ->
                    when (route) {
                        is Route.Home -> {
                            goNextScreen(Route.Home())
                        }
                        is Route.Register -> {
                            goNextScreen(Route.Register())
                        }
                        else -> {}
                    }
                }
                currentState.isDataAvailable?.let { result ->
                    if (result) {
                        CustomSnackBar.make(
                            requireContext(),
                            requireView(),
                            "Lütfen önce kayıt olunuz. ",
                            SnackType.ERROR
                        ).show()
                        viewModel.messageShown()
                    } else {
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                        requireActivity().overridePendingTransition(
                            R.anim.slide_in_left,
                            R.anim.slide_out_left
                        )
                        requireActivity().finish()
                    }
                    viewModel.clearData()
                }


            }
        }
    }

    private fun goNextScreen(route: Route, data: GoogleUserInfo? = null) {
        when (route) {
            Route.Home() -> {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_left
                )
                requireActivity().finish()
            }
            Route.Register() -> {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(data)
                findNavController().navigate(action)
            }
            else -> {}
        }
        viewModel.navigated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}