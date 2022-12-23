package com.engin.eagerbeaver.domain.auth.usecase

import android.text.TextUtils
import android.util.Patterns
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.common.domain.model.UserRole

class ValidateRegisterUser() {

    operator fun invoke(name:String,email:String,password:String,userName:String) :ValidateRegisterUserResult{
        return if(name.isNotBlank() && password.isNotBlank() && userName.isNotBlank() && email.isNotBlank()){
            if(password.length >= 6){
              if(isValidEmail(email.trim())){
                  ValidateRegisterUserResult.Success(name,email,password, userName)
              }else{
                  ValidateRegisterUserResult.Error(UiText.StringResource(R.string.error_email_validation))
              }
            } else
                ValidateRegisterUserResult.Error(UiText.StringResource(R.string.error_password_length))
        }else{
            ValidateRegisterUserResult.Error(UiText.StringResource(R.string.error_fields_empty))
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    sealed class ValidateRegisterUserResult(){
        data class Success(var name:String,var email:String,var password:String,var userName:String):ValidateRegisterUserResult()
        data class Error(val message: UiText):ValidateRegisterUserResult()
    }
}