package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class CheckMailReturnDto(
    @SerializedName("data")
    val data: DataCheckMailDto?,
    @SerializedName("error")
    val error: String?
)