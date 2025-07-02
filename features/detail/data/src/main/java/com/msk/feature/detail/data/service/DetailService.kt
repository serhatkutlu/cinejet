package com.msk.feature.detail.data.service

import com.msk.feature.detail.data.dto.MovieDetailDto
import com.msk.feature.detail.data.dto.MovieReviewsDto
import com.msk.feature.detail.data.dto.MovieVideosDto
import com.msk.feature.detail.data.util.Constants.MovieDetailQueryParams.CREDITS
import com.msk.feature.detail.data.util.Constants.MovieDetailQueryParams.IMAGES
import com.msk.feature.detail.data.util.Constants.MovieDetailQueryParams.RECOMMENDATIONS
import com.msk.network.result.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "$IMAGES,$CREDITS,$RECOMMENDATIONS"
    ): NetworkResult<MovieDetailDto>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): NetworkResult<MovieVideosDto>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): NetworkResult<MovieReviewsDto>


}