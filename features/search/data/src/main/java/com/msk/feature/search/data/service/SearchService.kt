package com.msk.feature.search.data.service

import com.msk.feature.search.data.dto.MovieSearchResponseDto
import com.msk.feature.search.data.endpoints.Path.DISCOVER_MOVIE
import com.msk.feature.search.data.endpoints.Path.SEARCH_MOVIE
import com.msk.feature.search.data.endpoints.Path.TRENDING_MOVIE
import com.msk.feature.search.data.util.Constants
import com.msk.network.result.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_MOVIE)
    suspend fun fetchSearch(
        @Query(Constants.SearchServiceFields.PAGE) page: Int = Constants.DEFAULT_PAGE,
        @Query(Constants.SearchServiceFields.QUERY) query: String
    ):NetworkResult<MovieSearchResponseDto>

    @GET(TRENDING_MOVIE)
    suspend fun fetchTrending(
        @Query(Constants.SearchServiceFields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ):NetworkResult<MovieSearchResponseDto>

    @GET(DISCOVER_MOVIE)
    suspend fun fetchDiscover(
        @Query(Constants.SearchServiceFields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ):NetworkResult<MovieSearchResponseDto>

}