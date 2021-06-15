package com.example.androidapptemplate.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor() : ViewModel() {
    private val _finishSplash = MutableLiveData<Unit>()
    val finishSplash: LiveData<Unit> = _finishSplash

    internal fun initialize() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(SPLASH_DISPLAY_TIME_MILLIS)
            _finishSplash.postValue(Unit)
        }
    }

    companion object {
        private const val SPLASH_DISPLAY_TIME_MILLIS = 2000L
    }
}