package com.example.vknewsclient.domain.entity.usecases

import com.example.vknewsclient.domain.entity.AuthState
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.PostComment
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateFlowUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke() : StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}