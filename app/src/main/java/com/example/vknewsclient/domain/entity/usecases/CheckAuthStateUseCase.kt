package com.example.vknewsclient.domain.entity.usecases

import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository

class CheckAuthStateUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.checkAuthState()
    }
}