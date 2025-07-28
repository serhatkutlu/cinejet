package com.msk.domain.usecase

import com.msk.domain.repository.MovieDetailRepository
import javax.inject.Inject

class SetMovieFavoriteUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {
    suspend operator fun invoke(movieId: Long, favorite: Boolean) =
        repository.setMovieFavorite(movieId, favorite)

}