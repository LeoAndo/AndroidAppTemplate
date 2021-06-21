package com.example.androidapptemplate.data.features.auth.di

import com.example.androidapptemplate.data.features.auth.repository.AuthRepositoryImpl
import com.example.androidapptemplate.domain.features.auth.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}