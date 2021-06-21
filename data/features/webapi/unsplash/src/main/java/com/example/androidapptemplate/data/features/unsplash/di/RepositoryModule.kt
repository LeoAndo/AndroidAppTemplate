package com.example.androidapptemplate.data.features.unsplash.di

import com.example.androidapptemplate.data.features.unsplash.repository.UnsplashRepository
import com.example.androidapptemplate.data.features.unsplash.repository.UnsplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    internal abstract fun bindUnsplashRepository(impl: UnsplashRepositoryImpl): UnsplashRepository
}