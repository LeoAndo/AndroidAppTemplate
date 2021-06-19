package com.example.androidapptemplate.data.features.webapi.unsplash.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidapptemplate.data.features.webapi.unsplash.api.UnsplashService
import com.example.androidapptemplate.data.features.webapi.unsplash.api.response.toModel
import com.example.androidapptemplate.domain.exception.BadRequestException
import com.example.androidapptemplate.domain.exception.NetworkException
import com.example.androidapptemplate.domain.exception.NotFoundException
import com.example.androidapptemplate.domain.exception.UnAuthorizedException
import com.example.androidapptemplate.domain.features.webapi.unsplash.model.UnSplashPhoto
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1
private const val UNSPLASH_LAST_PAGE_INDEX = 5
private val TAG = UnsplashPagingSource::class.java.simpleName

internal class UnsplashPagingSource(
    private val api: UnsplashService,
    private val query: String
) : PagingSource<Int, UnSplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        Log.d(
            TAG,
            "query: $query position: $position loadSize: ${params.loadSize}"
        )

        return try {
            val photos =
                api.searchPhotos(query, position, params.loadSize).results.map { it.toModel() }
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty() || position == UNSPLASH_LAST_PAGE_INDEX) null else position + 1 // paging終了するときは、nullをセット.
            )
        } catch (e: Throwable) {
            Log.d(TAG, "error: $e") // なぜかたまに、403になる時ある.
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        HttpURLConnection.HTTP_UNAUTHORIZED -> LoadResult.Error(
                            UnAuthorizedException()
                        )
                        HttpURLConnection.HTTP_BAD_REQUEST -> LoadResult.Error(
                            BadRequestException(
                                e.localizedMessage ?: "unknown error"
                            )
                        )
                        HttpURLConnection.HTTP_NOT_FOUND -> LoadResult.Error(
                            NotFoundException(
                                e.localizedMessage ?: "unknown error"
                            )
                        )
                        else -> LoadResult.Error(e)
                    }
                }
                is UnknownHostException, is ConnectException, is SocketTimeoutException -> LoadResult.Error(
                    NetworkException()
                )
                else -> LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnSplashPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}