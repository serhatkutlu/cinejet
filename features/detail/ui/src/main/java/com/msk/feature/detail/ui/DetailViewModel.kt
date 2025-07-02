package com.msk.feature.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import com.msk.common.util.onError
import com.msk.common.util.onLoading
import com.msk.common.util.onSuccess
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.domain.usecase.GetMovieDetailByIdUseCase
import com.msk.domain.usecase.GetMovieReviewsByIdUseCase
import com.msk.domain.usecase.GetMovieVideoByIdUseCase
import com.msk.model.detail.MovieDetail
import com.msk.model.detail.MovieVideo
import com.msk.model.detail.Review
import com.msk.feature.detail.ui.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailByIdUseCase,
    private val getMovieVideoByIdUseCase: GetMovieVideoByIdUseCase,
    private val getMovieReviewsByIdUseCase: GetMovieReviewsByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<DetailUiState, DetailUiEvent, DetailUiEffect>(DetailUiState()) {


    private val detailArgs: Detail = savedStateHandle.toRoute()

    private val detailId: Int = detailArgs.id


    init {
        loadMovieDetail(detailId)

    }
    override fun onEvent(event: DetailUiEvent) {
        when (event) {

            is DetailUiEvent.OnTabSelected -> {
                setState { copy(selectedTabIndex = event.index) }

                when {
                    event.index == 0 && !uiState.value.isDetailLoaded -> loadMovieDetail(detailId)
                    event.index == 1 && !uiState.value.isVideoLoaded ->
                        loadMovieVideos(movieId = detailId)
                    event.index == 2 && !uiState.value.isReviewLoaded -> loadMovieReviews(movieId = detailId)
                }

            }
        }
    }

//    private fun loadMovieDetail(movieId: Int) {
//        getMovieDetailUseCase(movieId)
//            .onEach { result ->
//                result.onSuccess { movieDetail ->
//                    setState {
//                        copy(
//                            movieDetail = movieDetail,
//                            isDetailLoaded = true,
//                            isLoading = false,
//                            error = null
//                        )
//                    }
//                }
//                result.onLoading {
//                    setState { copy(isLoading = true) }
//                }
//                result.onError { errorCategory, data ->
//                    setState {
//                        copy(
//                            error = errorCategory.messageKey,
//                            movieDetail = data,
//                            isLoading = false
//                        )
//                    }
//                }
//            }
//            .launchIn(viewModelScope)
 //   }
private fun loadMovieDetail(movieId: Int) {
    viewModelScope.launch {
        getMovieDetailUseCase(movieId)
            .collect{ result ->

                when (result) {
                    is com.msk.common.util.Resource.Success -> {
                        setState {
                            copy(
                                movieDetail = result.data,
                                isDetailLoaded = true,
                                isLoading = false,
                                error = null
                            )
                        }
                    }

                    is com.msk.common.util.Resource.Loading -> {
                        setState { copy(isLoading = true) }
                    }

                    is com.msk.common.util.Resource.Error -> {
                        setState {
                            copy(
                                error = null,
                                movieDetail = result.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }
    }

    }


    private fun loadMovieVideos(movieId: Int) {
        getMovieVideoByIdUseCase(movieId).onEach { movieVideo ->
            movieVideo.onSuccess {
                setState { copy(videos = it, isVideoLoaded = true) }
            }
            movieVideo.onLoading {
                setState { copy(isLoading = true) }
            }
            movieVideo.onError { errorCategory, data ->
                setState { copy(error = errorCategory.messageKey, videos = data) }
            }

        }.launchIn(viewModelScope)
    }

    private fun loadMovieReviews(movieId: Int) {

        val reviewsFlow = getMovieReviewsByIdUseCase(movieId)
        setState { copy(reviews = reviewsFlow, isReviewLoaded = true) }
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
    data class OnTabSelected(val index: Int) : DetailUiEvent()

}

sealed class DetailUiEffect {
}
