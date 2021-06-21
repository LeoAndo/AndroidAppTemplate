package com.example.androidapptemplate.data.features.trivia.di

import com.example.androidapptemplate.data.core.Network
import com.example.androidapptemplate.data.features.trivia.api.TriviaHeaderInterceptor
import com.example.androidapptemplate.data.features.trivia.api.TriviaService
import com.example.androidapptemplate.data.features.webapi.trivia.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Singleton
    @Provides
    internal fun provideTriviaService(): TriviaService {
        val okHttpClientBuilder = Network.createOkHttpClientBuilder(BuildConfig.DEBUG).apply {
            addInterceptor(TriviaHeaderInterceptor())
        }
        return Retrofit.Builder()
            .callFactory { request -> okHttpClientBuilder.build().newCall(request) }
            .baseUrl(BuildConfig.TRIVIA_API_DOMAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TriviaService::class.java)
    }
}