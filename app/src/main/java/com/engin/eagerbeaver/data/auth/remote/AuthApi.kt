package com.engin.eagerbeaver.data.auth.remote

import com.engin.eagerbeaver.data.auth.remote.dto.*
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/accounts/signup/")
    suspend fun registerUser(@Body body:RegisterBodyDto ) : Return

    @POST("/accounts/login/")
    suspend fun loginUser(@Body body:LoginBodyDto) : ReturnLoginDto

    @POST("/accounts/check_email/")
    suspend fun  checkMail(@Body body: CheckMailBodyDto ) : CheckMailReturnDto

}