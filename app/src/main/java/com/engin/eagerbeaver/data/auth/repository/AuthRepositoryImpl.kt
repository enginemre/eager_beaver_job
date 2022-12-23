package com.engin.eagerbeaver.data.auth.repository

import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.domain.model.UserRole
import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.common.presentation.util.UiText
import com.engin.eagerbeaver.data.auth.remote.AuthApi
import com.engin.eagerbeaver.data.auth.remote.dto.*
import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<ReturnLoginDto>> {
        return flow {
            try {
                emit(Resource.Loading())
                val loginBodyDto = LoginBodyDto(email = email, password = password)
                val result = authApi.loginUser(loginBodyDto)
                emit(Resource.Success(result))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun registerUser(
        name: String,
        userName: String,
        password: String,
        email: String,
        type: UserRole,
        interestId: List<Long>?,
        description: String?,
        title: String?
    ): Flow<Resource<Return>> {
        return flow {
            try {
                emit(Resource.Loading())
                val body = RegisterBodyDto(
                    description = description ?: "",
                    email = email,
                    fullName = name,
                    interestedWith = listOf<InterestedWithDto>(InterestedWithDto(id = 1)),
                    password = password,
                    title = title ?: "",
                    userType = if (type.name.startsWith("A")) "Applicant" else "Employer",
                    username = userName
                )
                val result = authApi.registerUser(body)
                emit(Resource.Success(result))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }

    override fun checkMail(email: String): Flow<Resource<CheckMailReturnDto>> {
        return flow {
            try {
                emit(Resource.Loading())
                val body = CheckMailBodyDto(email)
                val result = authApi.checkMail(body)
                emit(Resource.Success(result))
            } catch (e: SocketTimeoutException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_timeout)))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.error_internet)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        UiText.DynamicString(
                            e.localizedMessage ?: "Beklenmedik bir hata oluştu"
                        )
                    )
                )
            }
        }
    }
}