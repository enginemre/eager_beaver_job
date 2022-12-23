package com.engin.eagerbeaver.presentation.main.profile

import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText

data class ProfileState(
    var isLoading:Boolean = false,
    var user:ApplicantUser? = null,
    var errorMessage:UiText? = null,
    var warningMessage:UiText? = null,
    var route:Route? = null
)
