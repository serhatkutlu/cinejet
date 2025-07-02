package com.msk.domain.usecase

import com.msk.common.util.onSuccess
import com.msk.domain.repository.MovieDetailRepository
import com.msk.domain.util.Constants
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieVideoByIdUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    operator fun invoke(id: Int) = movieDetailRepository.loadMovieVideoById(id)
        .map { it.onSuccess { it.filter { it.type == Constants.TRAILER_TYPE } } }
}