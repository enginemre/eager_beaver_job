package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class CheckMailBodyDto(
    @SerializedName("email")
    val email: String
)