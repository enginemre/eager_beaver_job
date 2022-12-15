package com.engin.eagerbeaver.common.domain.model

import com.engin.eagerbeaver.common.domain.util.RecyclerViewItem

data class Category(
    val categoryName: String,
    override val id: Long,
    val categoryImageUrl: String,
) : RecyclerViewItem {
}