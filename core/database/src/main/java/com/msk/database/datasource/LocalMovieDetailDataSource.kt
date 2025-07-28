package com.msk.database.datasource

import android.util.Log
import com.msk.database.dao.detail.MovieDetailDao
import com.msk.database.model.detail.MovieDetailEntity
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LocalMovieDetailDataSource @Inject constructor(private val movieDetailDao: MovieDetailDao) {
    fun getMovieDetail(id: Int) = movieDetailDao.getById(id).distinctUntilChanged()
    suspend fun saveMovieDetail(movieDetail: MovieDetailEntity) = movieDetailDao.insert(movieDetail)
    suspend fun deleteMovieDetail(movieId: Long) = movieDetailDao.deleteById(movieId)
    suspend fun updateFavoriteMovieById(movieId: Long, isFavorite: Boolean) = movieDetailDao.updateFavoriteById(movieId, isFavorite)
}