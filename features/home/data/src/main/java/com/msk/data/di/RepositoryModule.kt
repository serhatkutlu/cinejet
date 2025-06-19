package com.msk.data.di

import com.msk.data.repository.MovieHomeRepositoryImpl
import com.msk.domain.repository.MovieHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)

interface RepositoryModule {


    @Binds
    @ViewModelScoped
    fun bindMovieHomeRepository(impl: MovieHomeRepositoryImpl): MovieHomeRepository

}