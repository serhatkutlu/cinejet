package com.msk.database.datasource

import androidx.paging.PagingSource
import com.msk.database.dao.seeall.MovieDao
import com.msk.database.dao.seeall.MovieRemoteKeyDao
import com.msk.database.model.movie.MovieEntity
import com.msk.database.model.movie.MovieRemoteKeyEntity
import com.msk.database.util.Constants.DEFAULT_PAGE_SIZE
import com.msk.database.util.RoomTransactionProvider
import com.msk.model.common.MediaType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalMovieDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRemoteKeyDao: MovieRemoteKeyDao,
    private val transactionProvider: RoomTransactionProvider
) {
    fun getByMediaType(mediaType: MediaType, pageSize: Int=DEFAULT_PAGE_SIZE): Flow<List<MovieEntity>> =
        movieDao.getMoviesByMediaTypeLimited(mediaType, pageSize)

    fun getPagingByMediaType(mediaType: MediaType): PagingSource<Int, MovieEntity> =
        movieDao.getPagingSourceByMediaType(mediaType)



    suspend fun insertMovies(
        movies: List<MovieEntity>,
        mediaType: MediaType
    ) {
        transactionProvider.withTransaction {
            movieDao.deleteMoviesByMediaType(mediaType)
            movieDao.insertMovies(movies)
        }
    }


    suspend fun getRemoteKeyByIdAndMediaType(id: Int, mediaType: MediaType) =
        movieRemoteKeyDao.getByIdAndMediaType(id, mediaType)

    suspend fun insertMoviesWithPaging(
        mediaType: MediaType,
        movies: List<MovieEntity>,
        remoteKeys: List<MovieRemoteKeyEntity>,
        isRefreshData: Boolean
    ) = transactionProvider.withTransaction {
        if (isRefreshData) {
            movieDao.deleteMoviesByMediaType(mediaType)
            movieRemoteKeyDao.deleteByMediaType(mediaType)
        }
        movieRemoteKeyDao.insertAll(remoteKeys)
        movieDao.insertMovies(movies)
    }


     fun searchMovies(query: String): PagingSource<Int, MovieEntity> =movieDao.searchMovies(query)
}
