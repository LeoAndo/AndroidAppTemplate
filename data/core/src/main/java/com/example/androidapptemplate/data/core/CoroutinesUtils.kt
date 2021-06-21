package com.example.androidapptemplate.core

import com.example.androidapptemplate.domain.exception.BadRequestException
import com.example.androidapptemplate.domain.exception.NetworkException
import com.example.androidapptemplate.domain.exception.NotFoundException
import com.example.androidapptemplate.domain.exception.UnAuthorizedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> apiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        // Log.w("apiCall", "currentThread: " + Thread.currentThread().name)
        try {
            apiCall.invoke()
        } catch (e: Throwable) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        HttpURLConnection.HTTP_UNAUTHORIZED -> throw UnAuthorizedException()
                        HttpURLConnection.HTTP_BAD_REQUEST -> throw BadRequestException(
                            e.localizedMessage ?: "unknown error"
                        )
                        HttpURLConnection.HTTP_NOT_FOUND -> throw NotFoundException(
                            e.localizedMessage ?: "unknown error"
                        )
                        else -> throw e
                    }
                }
                is UnknownHostException, is ConnectException, is SocketTimeoutException -> throw NetworkException()
                else -> throw e
            }
        }
    }
}

suspend fun <T> dbCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): T {
    return withContext(dispatcher) {
        // Log.w("dbCall", "currentThread: " + Thread.currentThread().name)
        try {
            apiCall.invoke()
        } catch (e: Throwable) {
            throw e
        }
    }
}
