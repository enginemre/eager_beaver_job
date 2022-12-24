package com.engin.eagerbeaver.data.main.remote.dto

data class DataAppliedJobDto(
    val applicant: String,
    val applicated_date: String,
    val enabled: Boolean,
    val id: Int,
    val job: JobDto,
    val job_image: String,
    val status: String
)