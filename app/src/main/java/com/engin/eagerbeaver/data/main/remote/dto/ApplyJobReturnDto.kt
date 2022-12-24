package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class ApplyJobReturnDto(
    @SerializedName("data")
    val `data`: DataAppliedJobDto?,
    @SerializedName("job_image")
    val jobİmage: String?,
    @SerializedName("error")
    val errorMessage: String?
)