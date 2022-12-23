package com.engin.eagerbeaver.data.main.remote.dto


import com.engin.eagerbeaver.common.domain.model.ApplicantUser
import com.engin.eagerbeaver.common.domain.model.EmployeeUser
import com.engin.eagerbeaver.data.auth.remote.dto.InterestedWithDto
import com.google.gson.annotations.SerializedName

data class DataProfileDto(
    @SerializedName("birth_date")
    val birthDate: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("interested_with")
    val interestedWith: List<InterestedWithDto>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("user")
    val user:UserDto
)