package com.example.vknewsclient.ui.theme

import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.PostComment

sealed class NewsFeedScreenState {
    object InitialState : NewsFeedScreenState()
    data class Posts(val posts : List<FeedPost>) : NewsFeedScreenState()
}