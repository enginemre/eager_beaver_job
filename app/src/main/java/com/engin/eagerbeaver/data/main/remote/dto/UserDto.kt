package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("date_joined")
    val dateJoined: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_login")
    val lastLogin: Any,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String
)