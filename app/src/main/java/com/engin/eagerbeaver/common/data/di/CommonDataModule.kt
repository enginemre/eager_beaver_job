package com.engin.eagerbeaver.common.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.engin.eagerbeaver.common.data.preferences.DefaultPreferences
import com.engin.eagerbeaver.common.domain.preferences.Preferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonDataModule {

    @Provides
    @Singleton
    fun providePreferences(
        gson: Gson,
        sharedPreferences: SharedPreferences
    ): Preferences {
        return DefaultPreferences(
            gson,
            sharedPreferences
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

}