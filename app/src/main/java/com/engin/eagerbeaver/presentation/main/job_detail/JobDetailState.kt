package com.engin.eagerbeaver.presentation.main.job_detail

import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.util.UiText

data class JobDetailState(
    var jobDetail: JobAdvert? = null,
    var isLoading:Boolean = false,
    var errorMessage: UiText? = null,
    var warningMessage:UiText? = null
) {
}