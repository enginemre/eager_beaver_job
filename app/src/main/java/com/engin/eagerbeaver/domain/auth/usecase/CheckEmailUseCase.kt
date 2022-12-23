package com.engin.eagerbeaver.domain.auth.usecase

import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.auth.remote.dto.CheckMailReturnDto
import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email:String): Flow<Resource<CheckMailReturnDto>> {
        return authRepository.checkMail(email)
    }
}