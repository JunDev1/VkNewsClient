@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.example.vknewsclient.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.NewsFeedViewModel
import com.example.vknewsclient.domain.FeedPost

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onCommentClickListener : (FeedPost) -> Unit
    ) {
    val viewModel : NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.InitialState)
    val currentState = screenState.value

    when (currentState) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                posts = currentState.posts,
                paddingValues = paddingValues,
                viewModel = viewModel,
                onCommentClickListener = onCommentClickListener
            )
        }
        NewsFeedScreenState.InitialState -> {}
    }
}

@Composable
private fun FeedPosts(
    posts: List<FeedPost>,
    paddingValues: PaddingValues,
    viewModel: NewsFeedViewModel,
    onCommentClickListener : (FeedPost) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 80.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            posts,
            key = { it.id }
        ) {
            val feedPost = it
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                background = {},
                dismissContent = {
                    PostCard(
                        feedPost = it,
                        onViewsItemListener = {
                            viewModel.updateCount(feedPost, it)
                        },
                        onShareClickListener = {
                            viewModel.updateCount(feedPost, it)
                        },
                        onLikeClickListener = {
                            viewModel.updateCount(feedPost, it)
                        },
                        onCommentClickListener = {
                            onCommentClickListener(feedPost)
                        },
                    )
                })

        }
    }
}