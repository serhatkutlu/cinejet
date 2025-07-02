package com.msk.feature.home.data.service


import com.msk.feature.home.data.dto.MovieResponseDto
import com.msk.feature.home.data.endpoints.Path
import com.msk.feature.home.data.util.Constants
import com.msk.network.result.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(Path.UPCOMING_MOVIE)
    suspend fun fetchUpcoming(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>

    @GET(Path.TOP_RATED_MOVIE)
    suspend fun fetchTopRated(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>

    @GET(Path.POPULAR_MOVIE)
    suspend fun fetchPopular(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>

    @GET(Path.NOW_PLAYING_MOVIE)
    suspend fun fetchNowPlaying(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>

    @GET(Path.DISCOVER_MOVIE)
    suspend fun fetchDiscover(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>

    @GET(Path.TRENDING_MOVIE)
    suspend fun fetchTrending(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<com.msk.feature.home.data.dto.MovieResponseDto>

}