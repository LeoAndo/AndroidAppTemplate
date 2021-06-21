package com.example.androidapptemplate.data.features.auth.datasource

import com.example.androidapptemplate.data.features.auth.response.LoginResponse
import com.example.androidapptemplate.data.features.auth.response.toModel
import com.example.androidapptemplate.domain.features.login.model.LoginUser
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

internal class AuthRemoteDataSource @Inject constructor(

) {

    suspend fun login(emailAddress: String, password: String): LoginUser {
        // dummy api call
        delay(2000L)
        return LoginResponse(
            id = UUID.randomUUID().toString(),
            userName = "Leo",
            age = 20,
            emailAddress = emailAddress
        ).toModel()
    }
}