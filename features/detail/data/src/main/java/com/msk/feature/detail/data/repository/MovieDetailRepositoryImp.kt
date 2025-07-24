package com.msk.feature.detail.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.msk.common.util.Resource
import com.msk.feature.detail.data.datasource.MovieDetailDataSource
import com.msk.feature.detail.data.mapper.toMovieDetail
import com.msk.feature.detail.data.mapper.toMovieVideo
import com.msk.feature.detail.data.paging.ReviewPagingSource
import com.msk.feature.detail.data.util.Constants.DEFAULT_REVIEWS_PAGE_SIZE
import com.msk.domain.repository.MovieDetailRepository
import com.msk.model.detail.MovieDetail
import com.msk.model.detail.MovieVideo
import com.msk.model.detail.Review
import com.msk.network.result.onError
import com.msk.network.result.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailRepositoryImp @Inject constructor(private val movieDetailDataSource: MovieDetailDataSource) :
    MovieDetailRepository {
    override fun loadMovieDetailById(id: Int): Flow<Resource<MovieDetail>> = flow {
        id
        val response = movieDetailDataSource.fetchMovieDetail(id)
        response.onSuccess {
            emit(Resource.Success(it.toMovieDetail()))

        }
        response.onError {
            emit(Resource.Error(it))

        }

    }

    override fun loadMovieVideoById(id: Int): Flow<Resource<List<MovieVideo>>> = flow {
        val response = movieDetailDataSource.fetchMovieVideo(id)
        response.onSuccess {
            emit(Resource.Success(it.toMovieVideo()))
        }
        response.onError {
            emit(Resource.Error(it))
        }
    }

    override fun loadMovieReviewsById(id: Int): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_REVIEWS_PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                ReviewPagingSource(
                    movieId = id, fetchReviews =
                        movieDetailDataSource::fetchMovieReviews
                )
            }
        ).flow

    }


}