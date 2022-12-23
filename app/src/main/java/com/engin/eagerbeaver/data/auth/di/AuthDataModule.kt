package com.engin.eagerbeaver.data.auth.di

import com.engin.eagerbeaver.common.data.util.AppService
import com.engin.eagerbeaver.data.auth.remote.AuthApi
import com.engin.eagerbeaver.data.auth.repository.AuthRepositoryImpl
import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthDataModule {


    @Provides
    @Singleton
    fun provideAuthApi(
        client: OkHttpClient
    ): AuthApi {
        return Retrofit.Builder()
            .baseUrl(AppService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi
    ): AuthRepository {
        return AuthRepositoryImpl(api)
    }

}