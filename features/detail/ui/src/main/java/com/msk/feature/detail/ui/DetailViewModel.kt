package com.msk.feature.detail.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.msk.common.util.onError
import com.msk.common.util.onLoading
import com.msk.common.util.onSuccess
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.domain.usecase.GetMovieDetailByIdUseCase
import com.msk.domain.usecase.GetMovieReviewsByIdUseCase
import com.msk.domain.usecase.GetMovieVideoByIdUseCase
import com.msk.domain.usecase.SetMovieFavoriteUseCase
import com.msk.model.detail.MovieDetail
import com.msk.model.detail.MovieVideo
import com.msk.model.detail.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailByIdUseCase,
    private val getMovieVideoByIdUseCase: GetMovieVideoByIdUseCase,
    private val getMovieReviewsByIdUseCase: GetMovieReviewsByIdUseCase,
    private val setMovieFavoriteUseCase: SetMovieFavoriteUseCase,
) : BaseViewModel<DetailUiState, DetailUiEvent, DetailUiEffect>(DetailUiState()) {

    private val currentMovieId = MutableStateFlow<Int?>(null)

    init {
        observeMovieDetail()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeMovieDetail() {
        currentMovieId
            .flatMapLatest { movieId ->
                if (movieId == null) emptyFlow()
                else getMovieDetailUseCase(movieId)
            }
            .onEach { result ->
                result.onSuccess { movieDetail ->
                    updateState {
                        copy(
                            movieDetail = movieDetail,
                            isDetailLoaded = true,
                            isLoading = false,
                            error = null
                        )
                    }
                }
                result.onLoading {
                    updateState { copy(isLoading = true) }
                }
                result.onError { errorCategory, data ->
                    updateState {
                        copy(
                            movieDetail = data,
                            error = errorCategory.messageKey,
                            isLoading = false
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.OnMovieIdChanged -> {
                if (currentMovieId.value==event.movieId) return
                currentMovieId.value = event.movieId
                updateState { DetailUiState(selectedTabIndex = 0) }
            }

            is DetailUiEvent.OnTabSelected -> {
                updateState { copy(selectedTabIndex = event.index) }
                when (event.index) {
                    1 -> loadMovieVideos(event.movieId)
                    2 -> loadMovieReviews(event.movieId)
                }
            }

            is DetailUiEvent.OnRecommendationClick -> setEffect {
                DetailUiEffect.NavigateToDetail(event.movieId)
            }

            is DetailUiEvent.OnBackClick -> setEffect { DetailUiEffect.NavigateBack }

            is DetailUiEvent.OnFavoriteClick -> onFavoriteClick(event.movieId, event.isFavorite)
        }
    }

    private fun onFavoriteClick(movieId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            setMovieFavoriteUseCase.invoke(movieId, isFavorite)
            updateState { copy(movieDetail = movieDetail?.copy(isFavorite = !isFavorite)) }
        }
    }

    private fun loadMovieVideos(movieId: Int) {
        getMovieVideoByIdUseCase(movieId)
            .onEach { result ->
                result.onSuccess { updateState { copy(videos = it, isVideoLoaded = true) } }
                result.onLoading { updateState { copy(isLoading = true) } }
                result.onError { errorCategory, data -> updateState { copy(error = errorCategory.messageKey, videos = data) } }
            }
            .launchIn(viewModelScope)
    }

    private fun loadMovieReviews(movieId: Int) {
        val reviewsFlow = getMovieReviewsByIdUseCase(movieId)
        updateState { copy(reviews = reviewsFlow, isReviewLoaded = true) }
    }
}


data class DetailUiState(
    val selectedTabIndex: Int = 0,

    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val isDetailLoaded: Boolean = false,

    val videos: List<MovieVideo>? = null,
    val isVideoLoaded: Boolean = false,

    val reviews: Flow<PagingData<Review>> = emptyFlow(),
    val isReviewLoaded: Boolean = false,

    val error: String? = null
)

sealed class DetailUiEvent {
    data class OnTabSelected(val index: Int, val movieId: Int) : DetailUiEvent()
    data class OnRecommendationClick(val movieId: Int) : DetailUiEvent()
    data class OnMovieIdChanged(val movieId: Int) : DetailUiEvent()
    data object OnBackClick : DetailUiEvent()
    data class OnFavoriteClick(val movieId: Long, val isFavorite: Boolean) : DetailUiEvent()
}

sealed class DetailUiEffect {
    data class NavigateToDetail(val movieId: Int) : DetailUiEffect()
    data object NavigateBack : DetailUiEffect()
}
