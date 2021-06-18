package com.example.androidapptemplate.data.di

import android.util.Log
import com.example.androidapptemplate.data.BuildConfig
import com.example.androidapptemplate.data.features.trivia.api.TriviaHeaderInterceptor
import com.example.androidapptemplate.data.features.trivia.api.TriviaService
import com.example.androidapptemplate.data.features.unsplash.api.UnsplashHeaderInterceptor
import com.example.androidapptemplate.data.features.unsplash.api.UnsplashService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    /**
     * https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/#okhttpclients-should-be-shared
     * シングルインスタンス化してアプリ内で使いまわした方がパフォーマンスがよくなるらしい。
     */
    @Singleton
    @Provides
    internal fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
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
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideTriviaService(okHttpClientBuilder: OkHttpClient.Builder): TriviaService {
        okHttpClientBuilder.addInterceptor(TriviaHeaderInterceptor())
        return Retrofit.Builder()
            .callFactory { request -> okHttpClientBuilder.build().newCall(request) }
            .baseUrl(BuildConfig.TRIVIA_API_DOMAIN)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TriviaService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideUnsplashService(
        okHttpClientBuilder: OkHttpClient.Builder,
        moshi: Moshi
    ): UnsplashService {
        okHttpClientBuilder.addInterceptor(UnsplashHeaderInterceptor())
        return Retrofit.Builder()
            .callFactory { request -> okHttpClientBuilder.build().newCall(request) }
            .baseUrl(BuildConfig.UNSPLASH_API_DOMAIN)
            // .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UnsplashService::class.java)
    }

    // http Log.
    private fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private const val TIMEOUT_SEC: Long = 15
    private val CONTENT_TYPE = "application/json".toMediaType()
}