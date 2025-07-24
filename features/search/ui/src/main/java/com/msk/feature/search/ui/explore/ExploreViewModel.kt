package com.msk.feature.search.ui.explore

import androidx.lifecycle.viewModelScope
import com.msk.common.util.MediaType
import com.msk.common.util.onSuccess
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.feature.domain.usecase.GetMoviesByMediaTypeUseCase
import com.msk.model.common.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        listOf(MediaType.Discover, MediaType.Trending).forEach { mediaType ->
            getMoviesByMediaTypeUseCase(mediaType)
                .onEach { result ->
                    result.onSuccess { movies ->
                        _mediaTypeMovies.update { old ->
                            old.toMutableMap().apply { put(mediaType, movies) }
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
}

sealed interface ExploreEffect {
    data class NavigateToMovieDetails(val movieId: Int) : ExploreEffect
    data class NavigateToSeeAll(val mediaType: MediaType) : ExploreEffect
    data object ClickSearch : ExploreEffect
}
