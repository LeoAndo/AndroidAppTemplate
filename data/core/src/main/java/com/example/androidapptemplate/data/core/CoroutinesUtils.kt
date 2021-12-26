package com.example.androidapptemplate.data.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * A generic class that holds a value or an exception
 */
internal sealed class SafeResult<out R> {
    data class Success<out T>(val data: T) : SafeResult<T>()
    data class Error(val errorResult: ErrorResult) : SafeResult<Nothing>()
}

sealed class ErrorResult : Exception() {
    data class UnAuthorizedError(override val message: String? = "UnAuthorizedError") :
        ErrorResult()

    data class BadRequestError(override val message: String? = "BadRequestError") :
        ErrorResult()

    data class NotFoundError(override val message: String? = "NotFoundError") :
        ErrorResult()

    data class NetworkError(override val message: String? = "NetworkError") :
        ErrorResult()

    data class OtherError(override val message: String? = "OtherError") :
        ErrorResult()
}

internal suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): SafeResult<T> {
    return withContext(dispatcher) {
        // Log.w("apiCall", "currentThread: " + Thread.currentThread().name)
        try {
            SafeResult.Success(apiCall.invoke())
        } catch (e: Throwable) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        HttpURLConnection.HTTP_UNAUTHORIZED -> {
                            SafeResult.Error(
                                ErrorResult.UnAuthorizedError(
                                    e.localizedMessage
                                )
                            )
                        }
                        HttpURLConnection.HTTP_BAD_REQUEST -> {
                            SafeResult.Error(
                                ErrorResult.BadRequestError(
                                    e.localizedMessage
                                )
                            )
                        }
                        HttpURLConnection.HTTP_NOT_FOUND -> {
                            SafeResult.Error(
                                ErrorResult.NotFoundError(
                                    e.localizedMessage
                                )
                            )
                        }
                        else -> {
                            SafeResult.Error(
                                ErrorResult.OtherError(
                                    e.localizedMessage
                                )
                            )
                        }
                    }
                }
                is UnknownHostException, is ConnectException, is SocketTimeoutException -> {
                    SafeResult.Error(
                        ErrorResult.NetworkError(
                            e.localizedMessage
                        )
                    )
                }
                else -> {
                    SafeResult.Error(
                        ErrorResult.OtherError(
                            e.localizedMessage
                        )
                    )
                }
            }
        }
    }
}

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
                        HttpURLConnection.HTTP_UNAUTHORIZED -> throw ErrorResult.UnAuthorizedError(e.localizedMessage)
                        HttpURLConnection.HTTP_BAD_REQUEST -> throw ErrorResult.BadRequestError(e.localizedMessage)
                        HttpURLConnection.HTTP_NOT_FOUND -> throw ErrorResult.NotFoundError(e.localizedMessage)
                        else -> throw ErrorResult.OtherError(e.localizedMessage)
                    }
                }
                is UnknownHostException, is ConnectException, is SocketTimeoutException -> {
                    throw ErrorResult.NetworkError(e.localizedMessage)
                }
                else -> throw ErrorResult.OtherError(e.localizedMessage)
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
            throw ErrorResult.OtherError(e.localizedMessage)
        }
    }
}
