package com.example.vknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vknewsclient.data.model.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application,
) : ViewModel() {
    private val repository = NewsFeedRepositoryImpl(application)
    private val getCommentsUseCase = GetCommentsUseCase(repository)
    val screenState =
        getCommentsUseCase(feedPost)
            .map { CommentsScreenState.Comments(comments = it, feedPost = feedPost) }
}
