package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class DataLoginDto(
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("user_id")
    val userId: Int
)