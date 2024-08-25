package com.example.vknewsclient.presentation.news

import com.example.vknewsclient.domain.entity.FeedPost

sealed class NewsFeedScreenState {
    object InitialState : NewsFeedScreenState()
    object Loading : NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>, val nextDataIsLoading: Boolean = false) :
        NewsFeedScreenState()
}