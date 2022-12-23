package com.engin.eagerbeaver.data.main.di

import com.engin.eagerbeaver.common.data.util.AppService
import com.engin.eagerbeaver.data.auth.remote.AuthApi
import com.engin.eagerbeaver.data.auth.repository.AuthRepositoryImpl
import com.engin.eagerbeaver.data.main.remote.MainApi
import com.engin.eagerbeaver.data.main.repository.MainRepositoryImpl
import com.engin.eagerbeaver.domain.auth.repository.AuthRepository
import com.engin.eagerbeaver.domain.main.repository.MainRepository
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
object MainDataModule {

    @Provides
    @Singleton
    fun provideMainApi(
        client: OkHttpClient
    ): MainApi {
        return Retrofit.Builder()
            .baseUrl(AppService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideMainRepository(
        api: MainApi
    ): MainRepository {
        return MainRepositoryImpl(api)
    }


}
