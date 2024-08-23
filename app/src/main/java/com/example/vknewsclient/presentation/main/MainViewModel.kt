package com.example.vknewsclient.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _stateAuthScreen = MutableLiveData<AuthState>(AuthState.Initial)
    val stateAuthScreen: LiveData<AuthState> = _stateAuthScreen

    init {
        val storage = VKPreferencesKeyValueStorage(application)
        val token = VKAccessToken.restore(storage)
        val loggedIn = token != null && token.isValid
        Log.d("MainViewModel", "Token: ${token?.accessToken}")
        _stateAuthScreen.value = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        if (result is VKAuthenticationResult.Success) {
            _stateAuthScreen.value = AuthState.Authorized
        } else {
            _stateAuthScreen.value = AuthState.NotAuthorized
        }
    }
}