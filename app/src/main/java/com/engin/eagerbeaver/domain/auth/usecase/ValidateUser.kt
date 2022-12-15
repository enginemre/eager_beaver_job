package com.engin.eagerbeaver.domain.auth.usecase

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.presentation.util.UiText

class ValidateUser() {
    operator fun invoke(userName:String,password:String) : ValidateUserResult {
        return  if(userName.isNotBlank() && password.isNotBlank()) {
            if(userName.length >= 8){
                if(password.length >= 4){
                    ValidateUserResult.Success(userName.trim(), password.trim())
                }else{
                    ValidateUserResult.Error(UiText.StringResource(R.string.password_length_error))
                }
            }else{
                ValidateUserResult.Error(UiText.StringResource(R.string.username_length_error))
            }
        }else{
            ValidateUserResult.Error(UiText.StringResource(R.string.fields_empty_error))
        }

    }

    sealed class ValidateUserResult(){
        data class Success(val userName: String,val password: String) : ValidateUserResult()
        data class Error(val message: UiText) : ValidateUserResult()
    }
}