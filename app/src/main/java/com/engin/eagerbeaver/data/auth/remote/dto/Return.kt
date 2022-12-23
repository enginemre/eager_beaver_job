package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class Return(
    @SerializedName("message")
    val message: String
)