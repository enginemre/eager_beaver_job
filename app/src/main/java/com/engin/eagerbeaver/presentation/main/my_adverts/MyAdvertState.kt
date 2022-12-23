package com.engin.eagerbeaver.presentation.main.my_adverts

import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText

data class MyAdvertState(
    var isLoading:Boolean= false,
    var myAdvertLists:List<JobAdvert> = emptyList(),
    var errorMessage:UiText?=null,
    var warningMessage:UiText? = null,
    var route:Route? = null
)
