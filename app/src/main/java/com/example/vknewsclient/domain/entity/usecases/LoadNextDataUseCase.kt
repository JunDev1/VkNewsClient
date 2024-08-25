package com.example.vknewsclient.domain.entity.usecases

import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNextDataUseCase@Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}