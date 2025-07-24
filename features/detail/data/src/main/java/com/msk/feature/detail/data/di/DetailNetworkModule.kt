package com.msk.feature.detail.data.di

import com.msk.feature.detail.data.service.DetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object DetailNetworkModule {


    @Provides
    fun provideMovieService(retrofit: Retrofit): DetailService {
        return retrofit.create()
    }


}