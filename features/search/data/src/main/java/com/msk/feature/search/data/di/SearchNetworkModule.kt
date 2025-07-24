package com.msk.feature.search.data.di

import com.msk.feature.search.data.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)

object SearchNetworkModule {

    @Provides
    fun provideSearchApiService(retrofit: Retrofit):SearchService {
        return retrofit.create()
    }
}