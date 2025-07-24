package com.msk.feature.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msk.feature.search.data.datasource.imp.SearchRemoteDataSource
import com.msk.feature.search.data.mapper.toDomain
import com.msk.model.common.Movie
import com.msk.network.result.NetworkResult


class SearchPagingSource(
    private val query: String,
    private val api: SearchRemoteDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val movies = when (val response = api.searchMoviesByQuery(query, page)) {
                is NetworkResult.Success -> response.data.results.map { it.toDomain() }
                is NetworkResult.Error -> emptyList()
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
}