package com.engin.eagerbeaver.presentation.main.my_applicant

import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.util.UiText

data class ApplicantState(
    var isLoading: Boolean = false,
    var errorMessage:UiText? =null,
    var myApplicant: List<JobAdvert>? = null
)
