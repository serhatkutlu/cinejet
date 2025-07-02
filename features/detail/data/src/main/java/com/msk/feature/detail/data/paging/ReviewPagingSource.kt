package com.msk.feature.detail.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msk.common.util.ErrorCategory
import com.msk.feature.detail.data.dto.MovieReviewsDto

import com.msk.feature.detail.data.mapper.toReview
import com.msk.model.detail.Review
import com.msk.network.result.NetworkResult

class ReviewPagingSource(
    private val movieId: Int,
    private val fetchReviews: suspend (Int,Int) -> NetworkResult<MovieReviewsDto>
) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        return try {
            when (val response = fetchReviews(movieId, page)) {
                is NetworkResult.Success -> {
                    val reviews = response.data.results.map { it.toReview() }
                    val totalPages = response.data.totalPages
                    LoadResult.Page(data = reviews, prevKey = if (page == 1) null else page - 1, nextKey  = if (page < totalPages) page + 1 else null)
                }
                is NetworkResult.Error -> {
                 LoadResult.Error(Throwable(response.error.messageKey))
                }
                else -> LoadResult.Error(Throwable(ErrorCategory.UnknownError.messageKey))

            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}