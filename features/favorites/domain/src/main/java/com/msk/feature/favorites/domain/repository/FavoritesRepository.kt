package com.msk.feature.favorites.domain.repository

import com.msk.model.detail.MovieDetail
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<MovieDetail>>
    suspend fun updateFavoriteMovies(id: Int, isFavorite: Boolean)
}