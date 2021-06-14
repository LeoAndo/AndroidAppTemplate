package com.example.androidapptemplate.features.trivia.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindTriviaRepository(impl: TriviaRepositoryImpl): TriviaRepository
}