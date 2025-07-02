package com.msk.feature.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.msk.common.util.MediaType
import com.msk.common.util.Resource
import com.msk.feature.home.data.util.networkBoundResource
import com.msk.feature.home.data.datasource.MovieHomeDataSource
import com.msk.feature.home.data.mapper.toDomainModel
import com.msk.feature.home.data.mapper.toEntity
import com.msk.feature.home.data.paging.MovieRemoteMediator
import com.msk.common.util.Constants.DEFAULT_MAX_PAGE_SIZE
import com.msk.database.datasource.LocalMovieDataSource
import com.msk.domain.repository.MovieHomeRepository
import com.msk.model.home.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

class MovieHomeRepositoryImpl @Inject constructor (
    private val databaseDataSource: LocalMovieDataSource,
    private val networkDataSource: MovieHomeDataSource,
    ): MovieHomeRepository {

    override  fun loadMoviesByMediaType(mediaType: MediaType,maxPage:Int): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            query = { databaseDataSource.getByMediaType(mediaType,maxPage)
                .map { it -> it.map { it.toDomainModel() } } },
            fetch = { networkDataSource.fetchMovie(mediaType,maxPage) },
            shouldFetch = { it.isEmpty() || isDataStale(it.first()) },
            saveFetchResult = { result->
                databaseDataSource.deleteByMediaTypeAndInsertMovies(mediaType,result.results.map { it.toEntity(mediaType) })}

        )

    @OptIn(ExperimentalPagingApi::class)
    override  fun loadMoviesByMediaTypePaging(mediaType: MediaType) =Pager(
        config = PagingConfig(DEFAULT_MAX_PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = MovieRemoteMediator(networkDataSource, databaseDataSource, mediaType),
        pagingSourceFactory = { databaseDataSource.getPagingByMediaType(mediaType) }

    ).flow.map { it.map { it.toDomainModel() } }

    private fun isDataStale(movie: Movie): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastFetchedTime = movie.lastFetchedTime
        return (currentTime - lastFetchedTime) > MOVIE_CACHE_TIME
    }



    companion object {
        private val MOVIE_CACHE_TIME = 1.days.inWholeMilliseconds
    }
}