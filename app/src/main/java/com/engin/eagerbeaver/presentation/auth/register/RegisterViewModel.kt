package com.engin.eagerbeaver.presentation.auth.register

import androidx.lifecycle.ViewModel
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.domain.auth.model.ApplicantUser
import com.engin.eagerbeaver.domain.auth.usecase.RegisterUseCases
import com.engin.eagerbeaver.domain.auth.usecase.ValidateRegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCases: RegisterUseCases,
    private val preferences: Preferences
) : ViewModel() {

    private var _state = MutableStateFlow(RegisterState())
     val state = _state.asStateFlow()


    fun messageShown() {
        _state.update {
            it.copy(
                errorMessage = null
            )
        }
    }

    fun registerUserValid(name:String,userName:String,password:String,email:String,type: UserRole){
        when(val resultUserRegister = useCases.validateRegisterUser(name,email,password,userName,type)){
            is ValidateRegisterUser.ValidateRegisterUserResult.Error -> {
                _state.update {
                    it.copy(
                        errorMessage =resultUserRegister.message
                    )
                }
            }
            is ValidateRegisterUser.ValidateRegisterUserResult.Success -> {
                _state.update {
                    it.copy(
                        nextPage = true,
                    )
                }
            }
        }
    }
    private fun saveUser(name:String,userName:String,password:String,email:String,type: UserRole,job:String){
        val user = ApplicantUser(
            name,email,userName,type,job
        )
        preferences.saveLogin(true)
        preferences.saveApplicantUser(user)
        preferences.saveUserType(type)
    }

    private fun registerUser(){
        goNextPage()
    }

    private fun goNextPage(){
        _state.update {
            it.copy(
                nextPage = true,
            )
        }
    }
}