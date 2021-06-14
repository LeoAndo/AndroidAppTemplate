package com.example.androidapptemplate.features.trivia.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    internal abstract fun bindTriviaUseCase(impl: TriviaUseCaseImpl): TriviaUseCase
}