package com.msk.feature.favorites.ui

import androidx.lifecycle.viewModelScope
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.feature.favorites.domain.usecase.GetFavoriteMoviesUseCase
import com.msk.feature.favorites.domain.usecase.UpdateFavoriteMovieUseCase
import com.msk.model.detail.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewmodel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val updateFavoriteMovieUseCase: UpdateFavoriteMovieUseCase
) : BaseViewModel<UiState, UiEvent, UiEffect>(UiState()) {

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
         getFavoriteMoviesUseCase().onEach {data->
            _uiState.update { it.copy(movies = data) }
        }.launchIn(viewModelScope)
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnMovieFavoriteStatusChanged -> {
                updateFavoriteMovie(event.movieId, event.isFavorite)
            }

            is UiEvent.OnMovieClicked -> setEffect { UiEffect.NavigateToDetail(event.movieId) }
        }
    }

    private fun updateFavoriteMovie(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteMovieUseCase(id.toInt(), isFavorite)
        }

    }
}

data class UiState(
    val movies: List<MovieDetail> = emptyList()
)

sealed class UiEffect{
    data class NavigateToDetail(val id:Long) : UiEffect()
}

sealed class UiEvent {
    data class OnMovieFavoriteStatusChanged(val movieId: Long, val isFavorite: Boolean) : UiEvent()
    data class OnMovieClicked(val movieId: Long) : UiEvent()
}
