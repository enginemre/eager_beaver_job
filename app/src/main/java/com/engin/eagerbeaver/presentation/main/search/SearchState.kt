package com.engin.eagerbeaver.presentation.main.search

import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.presentation.main.search.component.JobFilter

data class SearchState(
    var isLoading:Boolean = false,
    var jobsList: List<JobAdvert> = emptyList(),
    var errorMessage: UiText? = null,
    var warningMessage: UiText? = null,
    var filter:JobFilter? = null,
    var shouldNavigate: Route? = null,
    var categoryList: List<Category>? = null
) {
}