package com.engin.eagerbeaver.presentation.auth.login

import com.engin.eagerbeaver.common.presentation.util.UiText

data class LoginState(
    var isLogin:Boolean = false,
    var errorMessage : UiText? = null,
    var navigateNext:Boolean = false
) {
}