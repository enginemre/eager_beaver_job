package com.engin.eagerbeaver.common.domain.model

import com.engin.eagerbeaver.common.domain.util.RecyclerViewItem
import com.engin.eagerbeaver.domain.auth.model.User

data class JobAdvert(
    var company: User,
    var title : String,
    var position:String,
    var type: String,
    var category:Category,
    var salary:Int,
    var description: String,
    var cratedDate:String,
    var companyImageUrl:String,
    override val id: Long,
):RecyclerViewItem
