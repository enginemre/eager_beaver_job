package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class DataCheckMailDto(
    @SerializedName("date_joined")
    val dateJoined: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_login")
    val lastLogin: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String
)