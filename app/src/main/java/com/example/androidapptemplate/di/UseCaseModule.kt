package com.example.androidapptemplate.di

import com.example.androidapptemplate.domain.features.webapi.trivia.usecase.TriviaUseCase
import com.example.androidapptemplate.domain.features.webapi.trivia.usecase.TriviaUseCaseImpl
import com.example.androidapptemplate.domain.features.login.usecase.LoginUseCase
import com.example.androidapptemplate.domain.features.login.usecase.LoginUseCaseImpl
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

    @Binds
    abstract fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
}