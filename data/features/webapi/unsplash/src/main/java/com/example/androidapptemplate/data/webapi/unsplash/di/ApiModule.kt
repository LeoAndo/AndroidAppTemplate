package com.example.androidapptemplate.data.webapi.unsplash.di

import com.example.androidapptemplate.data.webapi.unsplash.BuildConfig
import com.example.androidapptemplate.data.webapi.unsplash.api.UnsplashHeaderInterceptor
import com.example.androidapptemplate.data.webapi.unsplash.api.UnsplashService
import com.example.androidtemplate.core.Network
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Singleton
    @Provides
    internal fun provideUnsplashService(): UnsplashService {
        val okHttpClientBuilder = Network.createOkHttpClientBuilder(BuildConfig.DEBUG).apply {
            addInterceptor(UnsplashHeaderInterceptor())
        }
        return Retrofit.Builder()
            .callFactory { request -> okHttpClientBuilder.build().newCall(request) }
            .baseUrl(BuildConfig.UNSPLASH_API_DOMAIN)
            // .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE))
            .addConverterFactory(MoshiConverterFactory.create(Network.createMoshi()))
            .build()
            .create(UnsplashService::class.java)
    }
    private val CONTENT_TYPE = "application/json".toMediaType()
}