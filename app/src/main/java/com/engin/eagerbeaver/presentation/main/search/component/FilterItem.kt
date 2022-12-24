package com.engin.eagerbeaver.presentation.main.search.component

import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.util.RecyclerViewItem

class FilterItem(
    var data:Category,
    var selected: Boolean = false,
    override val id: Long
) : RecyclerViewItem