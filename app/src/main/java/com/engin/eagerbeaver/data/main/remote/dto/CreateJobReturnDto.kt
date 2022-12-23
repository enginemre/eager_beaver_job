package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class CreateJobReturnDto(
    @SerializedName("company_image")
    val companyİmage: String,
    @SerializedName("data")
    val `data`: DataCreateJobDto
)