package com.msk.domain.usecase

import com.msk.common.util.Resource
import com.msk.common.util.formatRuntime
import com.msk.common.util.onSuccess
import com.msk.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    operator fun invoke(id: Int) = movieDetailRepository.loadMovieDetailById(id)
        .map {response->
            if (response is Resource.Success){
                val runtime = formatRuntime(response.data.runtime.toInt())
                Resource.Success(response.data.copy(runtime = runtime))
            }
            else response

        }
}