package com.example.androidapptemplate.data.core

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object Network {
    /**
     * https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/#okhttpclients-should-be-shared
     * TODO シングルインスタンス化してアプリ内で使いまわした方がパフォーマンスがよくなるらしい。
     */
    fun createOkHttpClientBuilder(debug: Boolean): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        if (debug) {
            builder.addInterceptor(createHttpLoggingInterceptor(debug))
        }
        return builder
    }

    fun createMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // http Log.
    private fun createHttpLoggingInterceptor(debug: Boolean): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        if (debug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    private const val TIMEOUT_SEC: Long = 1
}