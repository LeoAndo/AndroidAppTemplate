package com.example.androidapptemplate.data.webapi.unsplash.di

import com.example.androidapptemplate.data.webapi.unsplash.repository.UnsplashRepository
import com.example.androidapptemplate.data.webapi.unsplash.repository.UnsplashRepositoryImpl
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