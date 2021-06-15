package com.example.androidapptemplate.data.features.auth.api.response

import com.example.androidapptemplate.domain.features.login.model.LoginUser

data class LoginResponse(
    val id: String,
    val userName: String,
    val age: Int,
    val emailAddress: String,
)

fun LoginResponse.toModel(): LoginUser =
    LoginUser(
        id = this.id,
        userName = this.userName
    )