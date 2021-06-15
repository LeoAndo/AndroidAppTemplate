package com.example.androidapptemplate.data.datasource.api

import android.util.Log
import com.example.androidapptemplate.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    private fun createOkHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createHttpLoggingInterceptor())
        }
        return builder
    }

    @Singleton
    @Provides
    internal fun provideTriviaService(): TriviaService {
        val okHttpClientBuilder = createOkHttpClientBuilder();
        okHttpClientBuilder.addInterceptor(TriviaHeaderInterceptor())
        return Retrofit.Builder()
            .callFactory { request -> okHttpClientBuilder.build().newCall(request) }
            .baseUrl(BuildConfig.TRIVIA_API_DOMAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TriviaService::class.java)
    }

    // http Log.
    private fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    companion object {
        private const val TIMEOUT_SEC: Long = 15
    }
}