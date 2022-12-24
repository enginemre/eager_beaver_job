package com.engin.eagerbeaver.data.auth.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("message")
    val message:String?,
    @SerializedName("sucess")
    val success:Boolean?
) {
}