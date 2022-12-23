package com.engin.eagerbeaver.presentation.main.my_applicant.adapter

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.base.BaseAdapter
import com.engin.eagerbeaver.databinding.ItemApplicantBinding
import com.engin.eagerbeaver.databinding.ItemCategoryBinding
import com.engin.eagerbeaver.presentation.main.home.components.CategoryCardListener


class ApplicantAdapter(
    private val applicants: List<JobAdvert>,
) : BaseAdapter<ItemApplicantBinding, JobAdvert>(applicants)  {
    override val layoutId: Int = R.layout.item_applicant

    override fun bind(binding: ItemApplicantBinding, item: JobAdvert) {
        binding.apply {
            applicant = item
            executePendingBindings()
        }

    }
}