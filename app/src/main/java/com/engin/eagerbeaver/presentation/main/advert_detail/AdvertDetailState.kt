package com.engin.eagerbeaver.presentation.main.advert_detail

import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.model.JobPosition
import com.engin.eagerbeaver.common.domain.model.JobType
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText

data class AdvertDetailState(
    var isLoading:Boolean = false,
    var categoriesList:List<Category>?=null,
    var errorMessage:UiText? = null,
    var warningMessage:UiText?=null,
    var route:Route? = null,
)
