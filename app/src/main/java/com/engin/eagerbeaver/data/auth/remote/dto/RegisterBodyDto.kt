package com.engin.eagerbeaver.data.auth.remote.dto


import com.google.gson.annotations.SerializedName

data class RegisterBodyDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("interested_with")
    val interestedWith: List<InterestedWithDto>,
    @SerializedName("password")
    val password: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("birth_date")
    val birthDate:String,
)