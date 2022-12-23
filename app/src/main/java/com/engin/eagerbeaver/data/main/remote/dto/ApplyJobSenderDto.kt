package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class  ApplyJobSenderDto(
    @SerializedName("applicant")
    val applicant: Int,
    @SerializedName("job")
    val job: Int
)