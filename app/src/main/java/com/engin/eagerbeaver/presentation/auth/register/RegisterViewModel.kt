package com.engin.eagerbeaver.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.domain.auth.usecase.RegisterUseCases
import com.engin.eagerbeaver.domain.auth.usecase.ValidateRegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCases: RegisterUseCases,
    private val preferences: Preferences
) : ViewModel() {

    private var _state = MutableStateFlow(RegisterState())
     val state = _state.asStateFlow()

    private var registerJob : Job? = null


    fun messageShown() {
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun registerUserValid(name:String,userName:String,password:String,email:String,type: UserRole,interestId:List<Long>? = null,description:String? =null,title :String? =null){
        when(val resultUserRegister = useCases.validateRegisterUser(name,email,password,userName)){
            is ValidateRegisterUser.ValidateRegisterUserResult.Error -> {
                _state.update {
                    it.copy(
                        errorMessage =resultUserRegister.message
                    )
                }
            }
            is ValidateRegisterUser.ValidateRegisterUserResult.Success -> {
                registerJob?.cancel()
                registerJob = useCases.registerUserUseCase(name, userName, password, email, type,interestId, description, title).onEach {  resource->
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
                           resource.data?.let { result->
                               if((result.error?.success != null && result.error.success )|| result.message.equals("OK") ){
                                   _state.update {
                                       it.copy(
                                           isLoading = false,
                                           nextPage = true
                                       )
                                   }
                               }else{
                                   _state.update {
                                       it.copy(
                                           isLoading = false,
                                           errorMessage = UiText.DynamicString("Bir Hata oluştu")
                                       )
                                   }
                               }
                           }
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }



}