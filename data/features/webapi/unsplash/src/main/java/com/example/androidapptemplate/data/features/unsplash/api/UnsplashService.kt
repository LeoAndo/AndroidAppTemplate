package com.example.androidapptemplate.data.features.unsplash.api

import com.example.androidapptemplate.data.features.unsplash.api.response.UnsplashResponse
import com.example.androidapptemplate.data.features.webapi.unsplash.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UnsplashService {

    companion object {
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }

    /*
    次ページがなくなると、以下のレスポンスになる
    {
    "total": 0,
    "total_pages": 0,
    "results":[]
    }
     */
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UnsplashResponse
}