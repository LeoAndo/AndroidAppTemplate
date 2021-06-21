package com.example.androidapptemplate.data.features.unsplash.api

import com.example.androidapptemplate.data.features.webapi.unsplash.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class UnsplashHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        val originalUrl = request.url.toString()

        if (originalUrl.contains(BuildConfig.UNSPLASH_API_DOMAIN)) {
            requestBuilder.addHeader("Accept-Version", "v1")
            requestBuilder.addHeader("Authorization", "Client-ID ${UnsplashService.CLIENT_ID}")
        }
        return chain.proceed(requestBuilder.build())
    }
}