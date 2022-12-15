package com.engin.eagerbeaver.domain.auth.di

import com.engin.eagerbeaver.domain.auth.usecase.LoginUseCases
import com.engin.eagerbeaver.domain.auth.usecase.RegisterUseCases
import com.engin.eagerbeaver.domain.auth.usecase.ValidateRegisterUser
import com.engin.eagerbeaver.domain.auth.usecase.ValidateUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthDomainModule {

    @Provides
    fun provideLoginUseCases() = LoginUseCases(
        validateUser = ValidateUser()
    )

    @Provides
    fun provideRegisterUseCases() = RegisterUseCases(
        validateRegisterUser = ValidateRegisterUser()
    )
}