package com.engin.eagerbeaver.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.domain.auth.usecase.LoginUseCases
import com.engin.eagerbeaver.domain.auth.usecase.ValidateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases,
    private val preferences: Preferences
) : ViewModel() {

    private var _state = MutableStateFlow(LoginState())
    var state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(userName: String, password: String) {
        when(val validateUserResult = useCases.validateUser(userName,password)){
            is ValidateUser.ValidateUserResult.Error -> {
                _state.update {
                    it.copy(
                        isLogin = false,
                        errorMessage = validateUserResult.message
                    )
                }
            }
            is ValidateUser.ValidateUserResult.Success -> {
                _state.update {
                    it.copy(
                        isLogin = false,
                        navigateNext = true
                    )
                }
            }
        }
    }

    fun messageShown() {
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun isLogin(): Boolean {
       return preferences.isLogin()
    }
}
