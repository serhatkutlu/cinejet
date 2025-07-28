package com.msk.feature.seeall.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.feature.seeall.ui.navigation.SeeAll
import com.msk.features.see_all.domain.usecase.LoadMoviesByMediaTypeUseCase
import com.msk.model.common.MediaType
import com.msk.model.common.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
 class SeeAllViewModel @Inject constructor(
    private val loadMoviesByMediaTypeUseCase: LoadMoviesByMediaTypeUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<UiState, UiEvent, UiEffect>(UiState()) {

    private val detailArgs: SeeAll = savedStateHandle.toRoute()

    private val mediaType: MediaType = detailArgs.mediaType

    init {
        getMovies()
    }

    private fun getMovies() {
        _uiState.update { it.copy(mediaType = mediaType) }

        val moviesFlow = loadMoviesByMediaTypeUseCase(mediaType)
            .cachedIn(viewModelScope)
        _uiState.update { it.copy(movies = moviesFlow) }

    }
    override fun onEvent(event: UiEvent) {

    }


}


 data class UiState(
    val mediaType: MediaType?=null,
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)

 sealed class UiEffect

 sealed class UiEvent


