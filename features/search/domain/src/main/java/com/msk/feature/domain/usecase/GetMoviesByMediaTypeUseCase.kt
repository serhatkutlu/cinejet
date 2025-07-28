package com.msk.feature.domain.usecase

import com.msk.common.util.Resource
import com.msk.common.util.filterMoviesWithPosters
import com.msk.feature.domain.repository.SearchRepository
import com.msk.model.common.MediaType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesByMediaTypeUseCase @Inject constructor(private val repository: SearchRepository) {
    operator fun invoke(mediaType: MediaType) = repository.loadMoviesByMediaType(mediaType).map {resource ->

        if (resource is Resource.Success) {

                Resource.Success(resource.data.filterMoviesWithPosters())

        }else resource
    }

}