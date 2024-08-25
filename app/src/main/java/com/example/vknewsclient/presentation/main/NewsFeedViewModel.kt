package com.example.vknewsclient.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.data.model.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.usecases.ChangeLikeStatusUseCase
import com.example.vknewsclient.domain.entity.usecases.DeletePostUseCase
import com.example.vknewsclient.domain.entity.usecases.GetRecommendationsUseCase
import com.example.vknewsclient.domain.entity.usecases.LoadNextDataUseCase
import com.example.vknewsclient.extensions.mergeWith
import com.example.vknewsclient.presentation.news.NewsFeedScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)
    private val exceptionHandler = CoroutineExceptionHandler{_,_, ->
        Log.d("NewsFeedViewModel", "Exception caught by exception handler")
    }
    private val getRecommendationsUseCase = GetRecommendationsUseCase(repository)
    private val loadNextDataUseCase = LoadNextDataUseCase(repository)
    private val changeLikeStatusUseCase = ChangeLikeStatusUseCase(repository)
    private val deletePostUseCase = DeletePostUseCase(repository)
    private val recommendationFlow = getRecommendationsUseCase()
    private val loadNextDataEvents = MutableSharedFlow<Unit>()
    private val loadNextDataFlow = flow {
        loadNextDataEvents.collect {
            emit(NewsFeedScreenState.Posts(
                posts = recommendationFlow.value,
                nextDataIsLoading = true
            ))
        }
    }
    val screenState = getRecommendationsUseCase()
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)


    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost)
        }
    }
}