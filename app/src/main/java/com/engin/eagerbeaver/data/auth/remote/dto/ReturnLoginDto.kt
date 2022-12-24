package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class ReturnLoginDto(
    @SerializedName("data")
    val dataLoginDto: DataLoginDto?,
    @SerializedName("error")
    val error :String?
)