package com.msk.feature.favorites.domain.usecase

import com.msk.feature.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(private val repository: FavoritesRepository){
     operator fun invoke() = repository.getFavorites()
}