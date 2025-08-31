package com.msk.feature.favorite.data.repository


import com.msk.feature.favorite.data.datasource.LocalMovieFavoriteDataSource
import com.msk.feature.favorite.data.mapper.toMovieDetail
import com.msk.feature.favorites.domain.repository.FavoritesRepository
import com.msk.model.detail.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DEFAULT_MAX_PAGE_SIZE = 20

class FavoriteRepositoryImp @Inject constructor(private val dataSource: LocalMovieFavoriteDataSource): FavoritesRepository {

    override fun getFavorites(): Flow<List<MovieDetail>> = dataSource.getFavoriteMovies().map { movieDetailEntities ->
        movieDetailEntities.map { it.toMovieDetail() }
    }
    override suspend fun updateFavoriteMovies(id: Int, isFavorite: Boolean) {
        dataSource.setFavoriteMovie(id, isFavorite)

    }
}
