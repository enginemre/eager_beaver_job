package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class Return(
    @SerializedName("error")
    val error : ErrorDto?,
    @SerializedName("message")
    val message : String?
)