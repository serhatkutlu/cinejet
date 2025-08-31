package com.msk.feature.favorites.domain.usecase

import com.msk.feature.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class UpdateFavoriteMovieUseCase@Inject constructor(private val repository: FavoritesRepository){
    suspend operator fun invoke (id: Int, isFavorite: Boolean) = repository.updateFavoriteMovies(id, isFavorite)
}
