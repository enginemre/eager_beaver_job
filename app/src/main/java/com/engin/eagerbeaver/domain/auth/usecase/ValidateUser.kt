package com.engin.eagerbeaver.domain.auth.usecase

import android.text.TextUtils
import android.util.Patterns
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.presentation.util.UiText

class ValidateUser() {
    operator fun invoke(email:String, password:String) : ValidateUserResult {
        return  if(email.isNotBlank() && password.isNotBlank()) {
            if(isValidEmail(email.trim())){
                if(password.length >= 4){
                    ValidateUserResult.Success(email.trim(), password.trim())
                }else{
                    ValidateUserResult.Error(UiText.StringResource(R.string.password_length_error))
                }
            }else{
                ValidateUserResult.Error(UiText.StringResource(R.string.error_email_validation))
            }
        }else{
            ValidateUserResult.Error(UiText.StringResource(R.string.fields_empty_error))
        }

    }

    sealed class ValidateUserResult(){
        data class Success(val userName: String,val password: String) : ValidateUserResult()
        data class Error(val message: UiText) : ValidateUserResult()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}