package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class LoginBodyDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)