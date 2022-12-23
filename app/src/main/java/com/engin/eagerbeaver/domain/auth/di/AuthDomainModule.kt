package com.engin.eagerbeaver.domain.auth.di

import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import com.engin.eagerbeaver.domain.auth.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthDomainModule {

    @Provides
    fun provideLoginUseCases(
        authRepository: AuthRepository
    ) = LoginUseCases(
        validateUser = ValidateUser(),
        loginUserUseCase = LoginUserUseCase(authRepository),
        checkEmailUseCase = CheckEmailUseCase(authRepository)
    )

    @Provides
    fun provideRegisterUseCases(
        authRepository: AuthRepository
    ) = RegisterUseCases(
        validateRegisterUser = ValidateRegisterUser(),
        registerUserUseCase = RegisterUserUseCase(authRepository)
    )
}