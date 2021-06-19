package com.example.androidapptemplate.data.di

import com.example.androidapptemplate.data.features.auth.repository.AuthRepositoryImpl
import com.example.androidapptemplate.data.features.webapi.trivia.repository.TriviaRepositoryImpl
import com.example.androidapptemplate.data.features.webapi.unsplash.repository.UnsplashRepository
import com.example.androidapptemplate.data.features.webapi.unsplash.repository.UnsplashRepositoryImpl
import com.example.androidapptemplate.domain.features.auth.repository.AuthRepository
import com.example.androidapptemplate.domain.features.webapi.trivia.repository.TriviaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindTriviaRepository(impl: TriviaRepositoryImpl): TriviaRepository

    @Binds
    internal abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    internal abstract fun bindUnsplashRepository(impl: UnsplashRepositoryImpl): UnsplashRepository
}