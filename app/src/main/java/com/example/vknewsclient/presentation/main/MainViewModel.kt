package com.example.vknewsclient.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.model.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import com.example.vknewsclient.domain.entity.usecases.CheckAuthStateUseCase
import com.example.vknewsclient.domain.entity.usecases.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val repository = NewsFeedRepositoryImpl(application)
    private val getAuthStateFlowUseCase = GetAuthStateFlowUseCase(repository)
    private val checkAuthStateFlowUseCase = CheckAuthStateUseCase(repository)
    val stateAuthScreen = getAuthStateFlowUseCase()


    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateFlowUseCase
        }
    }
}