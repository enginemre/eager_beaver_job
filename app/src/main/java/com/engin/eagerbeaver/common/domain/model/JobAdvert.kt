package com.engin.eagerbeaver.common.domain.model

import com.engin.eagerbeaver.common.domain.util.RecyclerViewItem

data class JobAdvert(
    var company: User,
    var title : String,
    var position:JobPosition,
    var type: JobType,
    var category:Category,
    var salary:Int,
    var description: String,
    var cratedDate:String,
    var companyImageUrl:String,
    var status:String = "",
    override val id: Long,
):RecyclerViewItem
