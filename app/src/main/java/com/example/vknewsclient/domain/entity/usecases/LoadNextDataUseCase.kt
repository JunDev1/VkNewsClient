package com.example.vknewsclient.domain.entity.usecases

import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}