package com.example.vknewsclient.domain.entity.usecases

import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository

class ChangeLikeStatusUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.changeLikeStatus(feedPost)
    }
}