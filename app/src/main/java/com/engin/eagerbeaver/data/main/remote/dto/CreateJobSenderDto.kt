package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class CreateJobSenderDto(
    @SerializedName("category")
    val category: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("employer")
    val employer: Int,
    @SerializedName("job_type")
    val jobType: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("salary")
    val salary: Int,
    @SerializedName("title")
    val title: String
)