package com.msk.feature.domain.usecase

import com.msk.common.util.MediaType
import com.msk.feature.domain.repository.SearchRepository
import javax.inject.Inject

class GetMoviesByMediaTypeUseCase @Inject constructor(private val repository: SearchRepository) {
    operator fun invoke(mediaType: MediaType) = repository.loadMoviesByMediaType(mediaType)

}