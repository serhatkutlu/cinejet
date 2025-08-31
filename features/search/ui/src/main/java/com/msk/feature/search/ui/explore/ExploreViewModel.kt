package com.msk.feature.search.ui.explore

import androidx.lifecycle.viewModelScope
import com.msk.common.util.onError
import com.msk.common.util.onSuccess
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.feature.domain.usecase.GetMoviesByMediaTypeUseCase
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getMoviesByMediaTypeUseCase: GetMoviesByMediaTypeUseCase
) : BaseViewModel<ExploreState, ExploreEvent, ExploreEffect>(ExploreState()) {


    private val _mediaTypeMovies = MutableStateFlow<Map<MediaType, List<Movie>>>(emptyMap())
    val mediaTypeMovies: StateFlow<Map<MediaType, List<Movie>>> = _mediaTypeMovies.asStateFlow()


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadMediaTypeMovies()
    }


    private fun loadMediaTypeMovies() {
        updateState {copy(
            isLoading = true,
            error = null,
            movies = emptyMap()
        )
        }
        listOf(MediaType.Discover, MediaType.Trending).forEach { mediaType ->
            getMoviesByMediaTypeUseCase(mediaType)
                .onEach { result ->
                    result.onSuccess { movies ->
//                        _mediaTypeMovies.update { old ->
//                            old.toMutableMap().apply { put(mediaType, movies) }
//                        }
                        updateState { copy(
                            isLoading = false,
                            error = null,
                            movies = uiState.value.movies.plus(Pair(mediaType,movies))
                        ) }
                    }
                    result.onError { errorCategory, movies ->
                        updateState {
                            copy(
                                isLoading = false,
                                error = errorCategory.toString(),
                                movies = emptyMap()
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    override fun onEvent(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.OnMovieSelected -> {
                setEffect { ExploreEffect.NavigateToMovieDetails(event.movieId) }
            }

            is ExploreEvent.OnSeeAllClick -> {
                setEffect { ExploreEffect.NavigateToSeeAll(event.mediaType) }
            }
            is ExploreEvent.OnClickSearch -> {
                setEffect { ExploreEffect.ClickSearch }
            }
            is ExploreEvent.OnPullToRefresh -> {
                loadMediaTypeMovies()
            }
        }
    }
}




data class ExploreState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movies: Map<MediaType, List<Movie>> = emptyMap()
)

sealed interface ExploreEvent {
    data class OnSeeAllClick(val mediaType: MediaType) : ExploreEvent
    data class OnMovieSelected(val movieId: Int) : ExploreEvent
    data object OnClickSearch : ExploreEvent
    object OnPullToRefresh : ExploreEvent {

    }
}

sealed interface ExploreEffect {
    data class NavigateToMovieDetails(val movieId: Int) : ExploreEffect
    data class NavigateToSeeAll(val mediaType: MediaType) : ExploreEffect
    data object ClickSearch : ExploreEffect
}
