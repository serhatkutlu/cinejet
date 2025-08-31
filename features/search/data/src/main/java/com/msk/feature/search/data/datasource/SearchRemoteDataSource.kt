package com.msk.feature.search.data.datasource

import com.msk.feature.search.data.dto.MovieSearchResponseDto
import com.msk.feature.search.data.service.SearchService
import com.msk.model.common.MediaType
import com.msk.network.result.NetworkResult
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val service: SearchService) {
    suspend fun searchMoviesByQuery(query: String,page: Int) = service.fetchSearch(query = query,page = page)


    suspend fun fetchMovieWithMediaType(mediaType: MediaType): NetworkResult<MovieSearchResponseDto> {
        return when (mediaType) {
            MediaType.Trending -> service.fetchTrending()
            MediaType.Discover -> service.fetchDiscover()
            else -> service.fetchDiscover()
        }
    }
}


