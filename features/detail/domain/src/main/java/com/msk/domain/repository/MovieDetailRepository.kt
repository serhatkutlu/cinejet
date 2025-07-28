package com.msk.domain.repository

import androidx.paging.PagingData
import com.msk.common.util.Resource
import com.msk.model.detail.MovieDetail
import com.msk.model.detail.MovieVideo
import com.msk.model.detail.Review
import kotlinx.coroutines.flow.Flow


interface MovieDetailRepository  {

    fun loadMovieDetailById(id:Int): Flow<Resource<MovieDetail?>>
    fun loadMovieVideoById(id:Int): Flow<Resource<List<MovieVideo>>>
    fun loadMovieReviewsById(id:Int): Flow<PagingData<Review>>
    suspend fun setMovieFavorite(id:Long, isFavorite:Boolean)
}