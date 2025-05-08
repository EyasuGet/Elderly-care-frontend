package com.example.elderlycare2.di


import android.content.Context
import com.example.elderlycare2.data.remote.api.AuthApi
import com.example.elderlycare2.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideActualSessionManager(@ApplicationContext context: Context): ActualSessionManager {
//        return ActualSessionManager(context)
//    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi ): AuthRepository {
        return AuthRepository(authApi)
    }



}