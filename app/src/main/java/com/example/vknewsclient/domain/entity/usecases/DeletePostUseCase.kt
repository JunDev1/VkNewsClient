package com.example.vknewsclient.domain.entity.usecases

import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import javax.inject.Inject

class DeletePostUseCase @Inject constructor (
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.deletePost(feedPost)
    }
}