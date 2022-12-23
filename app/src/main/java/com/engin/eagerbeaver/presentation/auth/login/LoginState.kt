package com.engin.eagerbeaver.presentation.auth.login

import com.engin.eagerbeaver.common.domain.model.GoogleUserInfo
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText

data class LoginState(
    var isLoading : Boolean = false,
    var errorMessage : UiText? = null,
    var navigateNext: Route? =null,
    var isDataAvailable:Boolean? =null
) {
}