package com.example.vknewsclient.di

import android.content.Context
import com.example.vknewsclient.data.model.network.ApiFactory
import com.example.vknewsclient.data.model.network.ApiService
import com.example.vknewsclient.data.model.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.entity.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl : NewsFeedRepositoryImpl) : NewsFeedRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService() : ApiService {
            return ApiFactory.apiService
        }
        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context : Context
        ) : VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}