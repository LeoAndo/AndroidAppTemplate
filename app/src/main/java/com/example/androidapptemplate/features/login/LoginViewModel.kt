package com.example.androidapptemplate.features.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidapptemplate.domain.features.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val emailAddress = ObservableField<String>()
    val password = ObservableField<String>()
    val isEnableLoginButton = ObservableBoolean()
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    internal fun inputTextChanged() {
        isEnableLoginButton.set(
            !(emailAddress.get().isNullOrBlank() || password.get().isNullOrBlank())
        )
    }

    internal suspend fun login() {
        _loginSuccess.value =
            loginUseCase.login(emailAddress.get().orEmpty(), password.get().orEmpty())
    }
}