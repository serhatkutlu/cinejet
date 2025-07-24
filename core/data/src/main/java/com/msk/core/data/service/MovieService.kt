package com.msk.core.data.service


import com.msk.core.data.dto.MovieResponseDto
import com.msk.core.data.endpoints.Path
import com.msk.core.data.util.Constants
import com.msk.network.result.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(Path.UPCOMING_MOVIE)
    suspend fun fetchUpcoming(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<MovieResponseDto>

    @GET(Path.TOP_RATED_MOVIE)
    suspend fun fetchTopRated(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<MovieResponseDto>

    @GET(Path.POPULAR_MOVIE)
    suspend fun fetchPopular(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<MovieResponseDto>

    @GET(Path.NOW_PLAYING_MOVIE)
    suspend fun fetchNowPlaying(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<MovieResponseDto>

    @GET(Path.DISCOVER_MOVIE)
    suspend fun fetchDiscover(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<MovieResponseDto>

    @GET(Path.TRENDING_MOVIE)
    suspend fun fetchTrending(
        @Query(Constants.Fields.PAGE) page: Int = Constants.DEFAULT_PAGE
    ): NetworkResult<MovieResponseDto>

}