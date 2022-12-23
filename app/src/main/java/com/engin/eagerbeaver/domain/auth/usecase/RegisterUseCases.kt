package com.engin.eagerbeaver.domain.auth.usecase

import com.engin.eagerbeaver.common.domain.util.Resource
import com.engin.eagerbeaver.data.auth.remote.dto.Return
import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCases @Inject constructor(
    val validateRegisterUser: ValidateRegisterUser,
    val registerUserUseCase: RegisterUserUseCase
)