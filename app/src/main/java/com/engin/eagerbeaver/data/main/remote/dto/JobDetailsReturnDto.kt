package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class JobDetailsReturnDto(
    @SerializedName("company_image")
    val companyImage: String,
    @SerializedName("data")
    val `data`: DataJobDetailDto
)