package com.msk.feature.detail.data.di

import com.msk.feature.detail.data.repository.MovieDetailRepositoryImp
import com.msk.domain.repository.MovieDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface DetailRepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindMovieHomeRepository(impl: MovieDetailRepositoryImp): MovieDetailRepository
}