package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class SearchSenderDto(
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("job_type")
    val jobType: String?,
    @SerializedName("title")
    val title:String?
)