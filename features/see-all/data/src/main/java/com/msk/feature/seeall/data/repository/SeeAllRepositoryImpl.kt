package com.msk.feature.seeall.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.msk.common.util.Constants.DEFAULT_MAX_PAGE_SIZE
import com.msk.core.data.datasource.MovieRemoteDataSource
import com.msk.database.datasource.LocalMovieDataSource
import com.msk.feature.seeall.data.mapper.toDomainModel
import com.msk.feature.seeall.data.paging.MovieRemoteMediator
import com.msk.features.see_all.domain.repository.SeeAllRepository
import com.msk.model.common.MediaType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeeAllRepositoryImpl @Inject constructor(
    private val localDataSource: LocalMovieDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
) : SeeAllRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun loadMoviesByMediaTypePaging(mediaType: MediaType) = Pager(
        config = PagingConfig(
            pageSize = DEFAULT_MAX_PAGE_SIZE,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        remoteMediator = MovieRemoteMediator(remoteDataSource, localDataSource, mediaType),
        pagingSourceFactory = { localDataSource.getPagingByMediaType(mediaType) }

    ).flow.map { it.map { it.toDomainModel() } }


}