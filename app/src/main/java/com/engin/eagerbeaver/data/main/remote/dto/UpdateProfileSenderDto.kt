package com.engin.eagerbeaver.data.main.remote.dto

data class UpdateProfileSenderDto(
    val description: String,
    val email: String,
    val full_name: String,
    val title: String,
    val username: String
)