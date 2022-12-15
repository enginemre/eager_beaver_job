package com.engin.eagerbeaver.presentation.main.home.adpter

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.presentation.base.BaseAdapter
import com.engin.eagerbeaver.databinding.ItemCategoryBinding
import com.engin.eagerbeaver.presentation.main.home.components.CategoryCardListener

class CategoryAdapter(
    private val categories: List<Category>,
    private val listener: CategoryCardListener
) : BaseAdapter<ItemCategoryBinding, Category>(categories)  {
    override val layoutId: Int = R.layout.item_category

    override fun bind(binding: ItemCategoryBinding, item: Category) {
        binding.apply {
            category = item
            cardListener = listener
            executePendingBindings()
        }

    }
}