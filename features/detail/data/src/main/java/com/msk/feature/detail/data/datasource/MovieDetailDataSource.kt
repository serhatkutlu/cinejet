package com.msk.feature.detail.data.datasource

import com.msk.feature.detail.data.service.DetailService
import javax.inject.Inject

class MovieDetailDataSource @Inject constructor(private val detailService: DetailService) {

    suspend fun fetchMovieDetail(movieId: Int) = detailService.getMovieDetail(movieId)
    suspend fun fetchMovieVideo(movieId: Int) = detailService.getMovieVideos(movieId)
    suspend fun fetchMovieReviews(movieId: Int,page:Int) = detailService.getMovieReviews(movieId,page)


}