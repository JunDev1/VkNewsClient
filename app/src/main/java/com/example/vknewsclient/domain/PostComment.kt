package com.example.vknewsclient.domain

import com.example.vknewsclient.R

data class PostComment(
    val id : Int,
    val authorName : String = "Damdin",
    val authorAvatarId : Int = R.drawable.commentator,
    val commentText : String = "Long comment text",
    val publicationDate : String = "14.00"
)
