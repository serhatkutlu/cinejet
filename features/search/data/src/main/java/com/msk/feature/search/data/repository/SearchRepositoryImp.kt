package com.msk.feature.search.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.msk.common.util.Resource
import com.msk.common.util.isDataStale
import com.msk.core.network_helper.NetworkHelper
import com.msk.database.datasource.LocalMovieDataSource
import com.msk.feature.domain.repository.SearchRepository
import com.msk.feature.search.data.datasource.imp.SearchRemoteDataSource
import com.msk.feature.search.data.mapper.toDomainModel
import com.msk.feature.search.data.mapper.toEntity
import com.msk.feature.search.data.paging.SearchPagingSource
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import com.msk.network.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImp @Inject constructor(
    private val localDataSource: LocalMovieDataSource,
    private val remoteDataSource: SearchRemoteDataSource,
    networkHelper: NetworkHelper
) : SearchRepository {

    private val isOnline: Boolean = networkHelper.isInternetAvailable()


    override fun loadMoviesByMediaType(
        mediaType: MediaType
    ): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            query = {
                localDataSource.getByMediaType(mediaType)
                    .map { it -> it.map { it.toDomainModel() } }
            },
            fetch = { remoteDataSource.fetchMovieWithMediaType(mediaType) },
            shouldFetch = { it.isEmpty() || isDataStale(it.first().lastFetchedTime) },
            saveFetchResult = { result ->
                localDataSource.insertMovies(
                    result.results.map { it.toEntity(mediaType = mediaType) },
                    mediaType = mediaType
                )
            }

        )
    override  fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return if (isOnline) {
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    SearchPagingSource(
                        query = query,
                        api = remoteDataSource
                    )
                }
            ).flow
        } else {
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    localDataSource.searchMovies(query)
                }
            ).flow.map { pagingData ->
                pagingData.map { it.toDomainModel() }
            }
        }
    }

}



