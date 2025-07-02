package com.msk.domain.usecase

import com.msk.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieReviewsByIdUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository)  {
    operator fun invoke(id: Int) = movieDetailRepository.loadMovieReviewsById(id)
}