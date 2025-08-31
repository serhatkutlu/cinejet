package com.msk.feature.home.data.repository

import com.msk.common.util.Resource
import com.msk.core.data.datasource.MovieRemoteDataSource
import com.msk.database.datasource.LocalMovieDataSource
import com.msk.domain.repository.MovieHomeRepository
import com.msk.feature.home.data.mapper.toDomainModel
import com.msk.feature.home.data.mapper.toEntity
import com.msk.feature.home.data.util.Constants.DEFAULT_PAGE
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import com.msk.network.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieHomeRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMovieDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
) : MovieHomeRepository {

    override fun loadMoviesByMediaType(
        mediaType: MediaType
    ): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            query = {
                localDataSource.getByMediaType(mediaType)
                    .map { it -> it.map { it.toDomainModel() } }
            },
            fetch = { remoteDataSource.fetchMovie(mediaType, DEFAULT_PAGE) },
            shouldFetch = {true}, //it.isEmpty() || isDataStale(it.first().lastFetchedTime) },
            saveFetchResult = { result ->
                localDataSource.insertMovies(
                    result.results.map { it.toEntity(mediaType) },mediaType)
            }

        )




}