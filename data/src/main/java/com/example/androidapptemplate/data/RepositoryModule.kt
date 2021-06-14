package com.example.androidapptemplate.data

import com.example.androidapptemplate.data.datasource.repository.TriviaRepositoryImpl
import com.example.androidapptemplate.domain.repository.TriviaRepository
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