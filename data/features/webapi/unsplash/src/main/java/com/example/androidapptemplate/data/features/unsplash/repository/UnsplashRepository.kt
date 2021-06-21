package com.example.androidapptemplate.data.features.unsplash.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidapptemplate.data.features.unsplash.api.UnsplashService
import com.example.androidapptemplate.data.features.unsplash.datasource.UnsplashPagingSource
import com.example.androidapptemplate.domain.features.webapi.unsplash.model.UnSplashPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UnsplashRepository {
    fun getSearchResults(query: String): Flow<PagingData<UnSplashPhoto>>
}

internal class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashService
) : UnsplashRepository {
    override fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UnsplashPagingSource(
                    api, query
                )
            }
        ).flow
}