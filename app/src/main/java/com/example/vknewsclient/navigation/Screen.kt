package com.example.vknewsclient.navigation

sealed class Screen(
    val route : String
) {

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object FavouriteFeed : Screen(ROUTE_FAVOURITE)
    object ProfileFeed : Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile]"
    }

}