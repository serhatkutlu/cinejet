package com.msk.ui

import androidx.lifecycle.viewModelScope
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.domain.usecase.GetHomeMoviesUseCase
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeMoviesUseCase: GetHomeMoviesUseCase) :
    BaseViewModel<UiState, UiEvent, UiEffect>(UiState()) {


    init {
        getMovies()
    }

    override fun onEvent(event: UiEvent) {

        when (event) {
            is UiEvent.ShowSnackBar -> {
                setEffect { UiEffect.ShowSnackBar(event.message) }
            }
        }
    }

    private fun getMovies() {
        val movieTypes = listOf(
            MediaType.TopRated,
            MediaType.Popular,
            MediaType.Upcoming,
            MediaType.NowPlaying,
            MediaType.Trending,
        )
        getHomeMoviesUseCase.invoke(movieTypes).onEach { response ->
            when (response) {
                is com.msk.common.util.Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is com.msk.common.util.Resource.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, movies = response.data)
                    }
                }

                is com.msk.common.util.Resource.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, movies = response.data)
                    }
                }
            }
        }.launchIn(viewModelScope)

    }

}

data class UiState(
    val isLoading: Boolean = false,
    val movies: Map<MediaType, List<Movie>?>? = null,
    val error: String? = null
)

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}

sealed class UiEffect {
    data class ShowSnackBar(val message: String) : UiEffect()
}


