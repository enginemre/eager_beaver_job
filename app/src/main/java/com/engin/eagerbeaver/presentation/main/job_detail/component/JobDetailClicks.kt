package com.engin.eagerbeaver.presentation.main.job_detail.component

import com.engin.eagerbeaver.common.domain.model.JobAdvert

interface JobDetailClicks {
    fun onJobApply(job:JobAdvert)
    fun onRetryError()
}