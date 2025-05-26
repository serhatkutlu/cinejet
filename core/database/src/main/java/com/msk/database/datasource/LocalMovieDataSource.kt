package com.msk.database.datasource

import androidx.paging.PagingSource
import com.msk.common.util.MediaType
import com.msk.database.dao.movie.MovieDao
import com.msk.database.dao.movie.MovieRemoteKeyDao
import com.msk.database.model.MovieEntity
import com.msk.database.model.MovieRemoteKeyEntity
import com.msk.database.util.RoomTransactionProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalMovieDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRemoteKeyDao: MovieRemoteKeyDao,
    private val transactionProvider: RoomTransactionProvider
) {
    fun getByMediaType(mediaType: MediaType, pageSize: Int): Flow<List<MovieEntity>> =
        movieDao.getMoviesByMediaTypeLimited(mediaType, pageSize)

    fun getPagingByMediaType(mediaType: MediaType): PagingSource<Int, MovieEntity> =
        movieDao.getPagingSourceByMediaType(mediaType)

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun deleteByMediaTypeAndInsertMovies(
        mediaType: MediaType,
        movies: List<MovieEntity>
    ) = transactionProvider.withTransaction {
        movieDao.deleteMoviesByMediaType(mediaType)
        movieDao.insertMovies(movies)
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
}
