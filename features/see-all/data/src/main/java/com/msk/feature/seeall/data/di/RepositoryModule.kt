package com.msk.feature.seeall.data.di

import com.msk.feature.seeall.data.repository.SeeAllRepositoryImpl
import com.msk.features.see_all.domain.repository.SeeAllRepository
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
    fun bindMovieHomeRepository(impl: SeeAllRepositoryImpl): SeeAllRepository
}
