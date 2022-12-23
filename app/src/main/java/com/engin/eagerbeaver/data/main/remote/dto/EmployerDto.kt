package com.engin.eagerbeaver.data.main.remote.dto


import com.google.gson.annotations.SerializedName

data class EmployerDto(
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("user")
    val user: UserDto
)