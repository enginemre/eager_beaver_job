package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class JobsWithCategoryIdDto(
    @SerializedName("data")
    val `data`: List<DataJobsWithCategoryIdDto>
)