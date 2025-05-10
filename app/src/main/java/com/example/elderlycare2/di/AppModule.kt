package com.example.elderlycare2.di

import android.content.Context
import com.example.elderlycare2.data.api.ApiService
import com.example.elderlycare2.data.api.ScheduleApi
import com.example.elderlycare2.data.api.TasksApi
import com.example.elderlycare2.data.api.UserProfileApi
import com.example.elderlycare2.data.repository.AuthRepository
import com.example.elderlycare2.data.repository.ScheduleRepository
import com.example.elderlycare2.data.repository.TaskRepository
import com.example.elderlycare2.data.repository.UserProfileRepository
import com.example.elderlycare2.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService, sessionManager: SessionManager): AuthRepository {
        return AuthRepository(apiService, sessionManager)
    }

//    @Provides
//    @Singleton
//    fun provideNurseRepository(apiService: ApiService): NurseRepository {
//        return NurseRepository(apiService)
//    }

    @Provides
    @Singleton
    fun provideScheduleRepository(scheduleApi: ScheduleApi): ScheduleRepository {
        return ScheduleRepository(scheduleApi)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(tasksApi: TasksApi): TaskRepository {
        return TaskRepository(tasksApi)
    }

    @Provides
    @Singleton
    fun provideUserProfileRepository(userProfileApi: UserProfileApi): UserProfileRepository {
        return UserProfileRepository(userProfileApi)
    }
}