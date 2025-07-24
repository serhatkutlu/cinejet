package com.msk.feature.domain.usecase

import com.msk.feature.domain.repository.SearchRepository
import javax.inject.Inject

class SearchMoviesPagingUseCase @Inject constructor(private val repository: SearchRepository)
{
    operator fun invoke(query: String) = repository.searchMovies(query)
}