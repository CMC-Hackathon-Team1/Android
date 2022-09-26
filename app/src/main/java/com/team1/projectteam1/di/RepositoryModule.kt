package com.team1.projectteam1.di

import com.team1.projectteam1.data.api.UserService
import com.team1.projectteam1.data.repository.UserRepositoryImpl
import com.team1.projectteam1.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository =
        UserRepositoryImpl(userService)
}