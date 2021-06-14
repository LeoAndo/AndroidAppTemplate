package com.example.androidapptemplate.di

import com.example.androidapptemplate.domain.usecase.TriviaUseCase
import com.example.androidapptemplate.domain.usecase.TriviaUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// TODO domainに移動したい
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindTriviaUseCase(impl: TriviaUseCaseImpl): TriviaUseCase
}