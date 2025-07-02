package com.msk.feature.home.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.msk.common.util.MediaType
import com.msk.feature.home.data.datasource.MovieHomeDataSource
import com.msk.feature.home.data.mapper.toEntity
import com.msk.database.datasource.LocalMovieDataSource
import com.msk.database.model.MovieEntity
import com.msk.database.model.MovieRemoteKeyEntity
import com.msk.network.result.NetworkResult

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val movieService: MovieHomeDataSource,
    private val database: LocalMovieDataSource,
    private val mediaType: MediaType
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.prevPage?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        return try {
            val response = movieService.fetchMovie(mediaType, page)
            when (response) {
                is NetworkResult.Success -> {
                    val movies = response.data.results.map { it.toEntity(mediaType) }
                    val endOfPagination = movies.isEmpty()


                    val prevPage = if (page == 1) null else page - 1
                    val nextPage = if (endOfPagination) null else page + 1

                    val remoteKeys = movies.map { entity ->
                        MovieRemoteKeyEntity(
                            id = entity.id,
                            mediaType = mediaType,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    database.insertMoviesWithPaging(
                        mediaType = mediaType,
                        movies = movies,
                        remoteKeys = remoteKeys,
                        isRefreshData = loadType == LoadType.REFRESH
                    )
                    MediatorResult.Success(endOfPagination)
                }

                is NetworkResult.Error -> {
                    MediatorResult.Error(Exception(response.error.messageKey))
                }
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)

        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> database.getRemoteKeyByIdAndMediaType(movie.id, mediaType) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> database.getRemoteKeyByIdAndMediaType(movie.id, mediaType) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): MovieRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.getRemoteKeyByIdAndMediaType(id, mediaType)
            }
        }
    }
}
