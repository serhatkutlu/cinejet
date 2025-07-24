package com.msk.domain.usecase

import com.msk.common.util.MediaType
import com.msk.common.util.Resource
import com.msk.domain.repository.MovieHomeRepository
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeMoviesUseCase @Inject constructor(
    private val repository: MovieHomeRepository
) {
    operator fun invoke(movieTypes: List<MediaType>): Flow<Resource<Map<MediaType, List<Movie>?>>> {
        val flows: List<Flow<Resource<List<Movie>>>> = movieTypes.map { mediaType ->
            repository.loadMoviesByMediaType(mediaType)
        }

        return combine(flows) { resources: Array<Resource<List<Movie>>> ->
            when {
                resources.any { it is Resource.Loading } -> Resource.Loading
                resources.any { it is Resource.Error<*> } -> {
                    val error = resources.first { it is Resource.Error<*> } as Resource.Error<*>
                    val mapResult = movieTypes.zip(resources).associate { (mediaType, resource) ->
                        val data = when(resource) {
                            is Resource.Success -> resource.data.filterPoster()
                            is Resource.Error -> resource.data?.filterPoster()
                            else -> null
                        }
                        mediaType to data
                    }
                    Resource.Error(error.errorCategory, mapResult)
                }

                else -> {
                    val mapResult =
                        movieTypes.zip(resources).associate { (mediaType, resource) ->
                            val data = (resource as Resource.Success).data.filterPoster()
                            mediaType to data
                        }

                    Resource.Success(mapResult)
                }
            }
        }
    }

}

private fun List<Movie>.filterPoster()=filter {
        it.posterPath != null&& it.backdropPath != null
    }
