package com.example.androidapptemplate.data

import android.util.Log
import com.example.androidapptemplate.domain.exception.BadRequestErrorException
import com.example.androidapptemplate.domain.exception.NetworkErrorException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection

internal suspend fun <T> apiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        Log.w("apiCall", "currentThread: " + Thread.currentThread().name)
        try {
            apiCall.invoke()
        } catch (e: Throwable) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> throw NetworkErrorException("HTTP_GATEWAY_TIMEOUT")
                        HttpURLConnection.HTTP_UNAVAILABLE -> throw NetworkErrorException("HTTP_UNAVAILABLE")
                        HttpURLConnection.HTTP_UNAUTHORIZED -> throw NetworkErrorException("HTTP_UNAUTHORIZED")
                        HttpURLConnection.HTTP_BAD_REQUEST -> throw BadRequestErrorException("HTTP_BAD_REQUEST")
                        HttpURLConnection.HTTP_NOT_FOUND -> throw NetworkErrorException("HTTP_NOT_FOUND")
                        else -> throw e
                    }
                }
                else -> throw e
            }
        }
    }
}

internal suspend fun <T> dbCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        Log.w("dbCall", "currentThread: " + Thread.currentThread().name)
        try {
            apiCall.invoke()
        } catch (e: Throwable) {
            throw e
        }
    }
}
