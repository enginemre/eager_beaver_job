package com.engin.eagerbeaver.common.presentation.adapter

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.presentation.base.BaseAdapter
import com.engin.eagerbeaver.databinding.ItemJobCardBinding
import com.engin.eagerbeaver.presentation.main.home.components.JobCardListener

class JobAdvertAdapter(
    private val adverts: List<JobAdvert>,
    private val listener: JobCardListener
) : BaseAdapter<ItemJobCardBinding, JobAdvert>(adverts)  {
    override val layoutId: Int = R.layout.item_job_card

    override fun bind(binding: ItemJobCardBinding, item: JobAdvert) {
        binding.apply {
            advert = item
            cardListener = listener
            executePendingBindings()
        }

    }
}