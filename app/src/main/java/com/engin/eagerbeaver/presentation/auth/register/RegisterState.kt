package com.engin.eagerbeaver.presentation.auth.register

import com.engin.eagerbeaver.common.presentation.util.UiText

data class RegisterState(
    var isLoading : Boolean = false,
    var errorMessage: UiText? = null,
    var nextPage:Boolean = false
)