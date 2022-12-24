package com.engin.eagerbeaver.presentation.main.search.adapter

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.presentation.base.BaseAdapter
import com.engin.eagerbeaver.databinding.ItemFilterCategoryBinding
import com.engin.eagerbeaver.presentation.main.search.component.FilterClick
import com.engin.eagerbeaver.presentation.main.search.component.FilterItem

class FilterAdapter(
    private val list: List<FilterItem>,
    private val listenerMy:FilterClick
) : BaseAdapter<ItemFilterCategoryBinding, FilterItem>(list) {
    override val layoutId: Int = R.layout.item_filter_category
    override fun bind(binding: ItemFilterCategoryBinding, item: FilterItem) {
        binding.apply {
            filterItem = item
            listener = listenerMy
            executePendingBindings()
        }
    }


}