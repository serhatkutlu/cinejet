package com.msk.feature.favorite.data.datasource

import com.msk.database.dao.favorites.FavoritesDao
import javax.inject.Inject

class LocalMovieFavoriteDataSource @Inject constructor(private val favoritesDao: FavoritesDao) {
    fun getFavoriteMovies() = favoritesDao.getAll()
    suspend fun setFavoriteMovie(id: Int, isFavorite: Boolean) =
        favoritesDao.updateFavourite(id, isFavorite)
}