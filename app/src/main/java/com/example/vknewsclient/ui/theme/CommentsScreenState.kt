package com.example.vknewsclient.ui.theme

import com.example.vknewsclient.domain.FeedPost
import com.example.vknewsclient.domain.PostComment

sealed class CommentsScreenState {
    object InitialState : CommentsScreenState()
    data class Comments(
        val comments: List<PostComment>,
        val posts: FeedPost
    ) : CommentsScreenState()
}