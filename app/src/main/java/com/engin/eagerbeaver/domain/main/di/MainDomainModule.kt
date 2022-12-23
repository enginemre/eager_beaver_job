package com.engin.eagerbeaver.domain.main.di

import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.engin.eagerbeaver.domain.main.repository.MainRepository
import com.engin.eagerbeaver.domain.main.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainDomainModule {

    @Provides
    @Singleton
    fun provideHomeUseCases(
        mainRepository: MainRepository
    ) = HomeUseCases(
        getCategoriesUseCase = GetCategoriesUseCase(mainRepository),
        getJobsWithCategoryId = GetJobsWithCategoryId(mainRepository)
    )


    @Provides
    @Singleton
    fun provideJobDetailUseCases(
        mainRepository: MainRepository
    ) = JobDetailUseCases(
        getJobInfoByIdUseCase = GetJobInfoByIdUseCase(mainRepository),
        applyJobUseCase = ApplyJobUseCase(mainRepository)
    )

    @Provides
    @Singleton
    fun provideAdvertDetailUseCases(
        mainRepository: MainRepository,
        preferences: Preferences
    ) = AdvertDetailUseCases(
        getJobInfoByIdUseCase = GetJobInfoByIdUseCase(mainRepository),
        deleteAdvertUseCase = DeleteAdvertUseCase(mainRepository),
        getCategoriesUseCase = GetCategoriesUseCase(mainRepository),
        updateAdvertUseCase = UpdateAdvertUseCase(mainRepository),
        createJobUseCase = CreateJobUseCase(mainRepository,preferences),
        getUserInfoUseCase = GetUserInfoUseCase(mainRepository)
    )

    @Provides
    @Singleton
    fun provideMyAdvertUseCases(
        mainRepository: MainRepository
    ) = MyAdvertsUseCases(
        getMyAdvertsById = GetMyAdvertsById(mainRepository)
    )


}