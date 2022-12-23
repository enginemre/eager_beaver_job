package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class DataAppiedJobDto(
    @SerializedName("applicant")
    val applicant: EmployerDto,
    @SerializedName("applicated_date")
    val applicatedDate: String,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: JobDto,
    @SerializedName("job_image")
    val jobİmage: String,

    @SerializedName("status")
    val status: String,
    @SerializedName("category")
    val category: DataCategoryDto
)