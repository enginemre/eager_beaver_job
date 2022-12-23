package com.engin.eagerbeaver.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.domain.auth.usecase.LoginUseCases
import com.engin.eagerbeaver.domain.auth.usecase.ValidateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: LoginUseCases,
    private val preferences: Preferences,
) : ViewModel() {

    private var _state = MutableStateFlow(LoginState())
    var state: StateFlow<LoginState> = _state.asStateFlow()


    private var loginUserJob: Job? =null
    private var checkEmailJob: Job? = null


    fun login(email: String, password: String) {
        when(val validateUserResult = useCases.validateUser(email,password)){
            is ValidateUser.ValidateUserResult.Error -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = validateUserResult.message
                    )
                }
            }
            is ValidateUser.ValidateUserResult.Success -> {
                loginUserJob?.cancel()
                loginUserJob =  useCases.loginUserUseCase(email, password).onEach { resource ->
                    when(resource){
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = resource.message
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }
                        is Resource.Success -> {
                            val result = resource.data
                            result?.let {
                                preferences.saveLogin(true)
                                preferences.saveUserType(if(it.dataLoginDto.userType.startsWith("A")) UserRole.APPLICANT else UserRole.EMPLOYEE)
                                preferences.saveUserID(it.dataLoginDto.userId.toLong())
                            }
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    navigateNext = Route.Home()
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)

            }
        }
    }


    fun checkEmailExist(mail:String){
        checkEmailJob?.cancel()
        checkEmailJob = useCases.checkEmailUseCase(mail).onEach {  resource->
            when(resource){
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = resource.message
                        )
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Success -> {
                    if(resource.data != null && resource.data.error.isNullOrBlank()){
                        preferences.saveLogin(true)
                        preferences.saveUserType(UserRole.APPLICANT)
                        preferences.saveUserID(resource.data.data.id.toLong())
                        _state.update {
                            it.copy(
                                isLoading = false,
                                navigateNext = Route.Home(),
                            )
                        }
                    }else{
                        _state.update {
                            it.copy(
                                isLoading = false,
                                navigateNext = Route.Register(),
                                isDataAvailable = true
                            )
                        }
                    }

                }
            }
        }.launchIn(viewModelScope)
    }

    fun loginAsGuest(){
        preferences.saveLogin(true)
        preferences.saveUserType(UserRole.APPLICANT)
        preferences.saveApplicantUser(ApplicantUser(id = 0))
       _state.update {
            it.copy(
                navigateNext = Route.Home()
            )
        }
    }

    fun messageShown() {
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun navigated(){
        _state.update {
            it.copy(
                navigateNext = null,
                isDataAvailable = false
            )
        }
    }

    fun isLogin(): Boolean {
       return preferences.isLogin()
    }

    fun clearData() {
        _state.update {
            it.copy(
                isDataAvailable = null
            )
        }
    }
}
