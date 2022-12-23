package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class ProfileBodyDto(
    @SerializedName("data")
    val `data`: DataProfileDto,
    @SerializedName("email")
    val email: String,
    @SerializedName("user_type")
    val userType: String
)