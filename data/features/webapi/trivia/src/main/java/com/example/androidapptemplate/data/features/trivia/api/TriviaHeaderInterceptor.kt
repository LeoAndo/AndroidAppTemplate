package com.example.androidapptemplate.data.features.trivia.api

import com.example.androidapptemplate.data.features.webapi.trivia.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val CONTENT_TYPE = "Content-Type"

internal class TriviaHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        val originalUrl = request.url.toString()

        if (originalUrl.contains(BuildConfig.TRIVIA_API_DOMAIN) && request.header(CONTENT_TYPE)
                .isNullOrEmpty()
        ) {
            requestBuilder.addHeader(CONTENT_TYPE, "text/plain;charset=UTF-8")
        }
        return chain.proceed(requestBuilder.build())
    }
}