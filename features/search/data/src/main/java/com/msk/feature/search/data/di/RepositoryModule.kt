package com.msk.feature.search.data.di

import com.msk.feature.domain.repository.SearchRepository
import com.msk.feature.search.data.repository.SearchRepositoryImp
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
    abstract fun bindSearchRepository(SearchRepositoryImpl: SearchRepositoryImp): SearchRepository

}