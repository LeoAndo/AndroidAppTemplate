package com.example.androidapptemplate.data.features.auth.response

import com.example.androidapptemplate.domain.features.login.model.LoginUser

internal data class LoginResponse(
    val id: String,
    val userName: String,
    val age: Int,
    val emailAddress: String,
)

internal fun LoginResponse.toModel(): LoginUser =
    LoginUser(
        id = this.id,
        userName = this.userName
    )