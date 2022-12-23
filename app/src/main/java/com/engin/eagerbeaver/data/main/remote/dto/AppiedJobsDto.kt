package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class AppiedJobsDto(
    @SerializedName("data")
    val `data`: List<DataAppiedJobDto>
)