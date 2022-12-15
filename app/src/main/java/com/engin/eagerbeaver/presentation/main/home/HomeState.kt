package com.engin.eagerbeaver.presentation.main.home

import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText

data class HomeState(
    var isLoading:Boolean = false,
    var categories:List<Category> = emptyList(),
    var lastAdverts: List<JobAdvert> = emptyList(),
    var errorMessage:UiText? = null,
    var warningMessage:UiText? = null,
    var shouldNavigate: Route? =null
)
