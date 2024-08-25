package com.example.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.data.model.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentsUseCase : GetCommentsUseCase,
) : ViewModel() {
    val screenState =
        getCommentsUseCase(feedPost)
            .map { CommentsScreenState.Comments(comments = it, feedPost = feedPost) }
}
