package com.msk.feature.favorite.data.di

import com.msk.feature.favorite.data.repository.FavoriteRepositoryImp
import com.msk.feature.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface FavoriteRepositoryModule {

    @Binds
    fun bindFavoriteRepository(impl: FavoriteRepositoryImp): FavoritesRepository

}