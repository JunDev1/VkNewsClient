package com.example.vknewsclient.presentation.comments

import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.PostComment

sealed class CommentsScreenState {
    object InitialState : CommentsScreenState()
    data class Comments(
        val comments: List<PostComment>,
        val feedPost: FeedPost
    ) : CommentsScreenState()
}