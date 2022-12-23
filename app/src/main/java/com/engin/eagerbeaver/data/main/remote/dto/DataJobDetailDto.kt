package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class DataJobDetailDto(
    @SerializedName("category")
    val category: DataCategoryDto,
    @SerializedName("country")
    val country: String,
    @SerializedName("date_published")
    val datePublished: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("employer")
    val employer: EmployerDto,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job_type")
    val jobType: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("title")
    val title: String
)