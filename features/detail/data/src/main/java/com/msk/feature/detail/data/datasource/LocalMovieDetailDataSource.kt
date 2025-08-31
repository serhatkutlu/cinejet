package com.msk.feature.detail.data.datasource

import com.msk.database.dao.detail.MovieDetailDao
import com.msk.database.model.detail.MovieDetailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMovieDetailDataSource @Inject constructor(private val movieDetailDao: MovieDetailDao) {
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity?> {
        return if (id==-1){
            movieDetailDao.getAny()
        }else movieDetailDao.getById(id)
    }
    suspend fun saveMovieDetail(movieDetail: MovieDetailEntity) = movieDetailDao.insert(movieDetail)
    suspend fun deleteMovieDetail(movieId: Long) = movieDetailDao.deleteById(movieId)
    suspend fun updateFavoriteMovieById(movieId: Long, isFavorite: Boolean) = movieDetailDao.updateFavoriteById(movieId, isFavorite)
}