package com.msk.feature.search.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.msk.common.util.MediaType
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.feature.domain.usecase.SearchMoviesPagingUseCase
import com.msk.model.common.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMoviesPagingUseCase: SearchMoviesPagingUseCase) :
    BaseViewModel<SearchUiState, SearchEvent, SearchEffect>(SearchUiState()) {


    init {
        observeSearchQuery()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            uiState
                .map { it.query }
                .debounce(300)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.isBlank()) flowOf(PagingData.empty())
                    else searchMoviesPagingUseCase(query)
                }
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    updateState { copy(results = pagingData) }
                }
        }
    }
    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChanged -> updateState { uiState.value.copy(query = event.query) }
            is SearchEvent.OnBackPressed -> setEffect { SearchEffect.NavigateBack }
            is SearchEvent.OnMovieClicked->setEffect {SearchEffect.NavigateDetail(id = event.id)  }
            else -> {}
        }
    }
}

data class SearchUiState(
    val query: String = "",
    val results: PagingData<Movie> = PagingData.empty()
)

sealed class SearchEvent {
    data class OnQueryChanged(val query: String) : SearchEvent()
    data object OnBackPressed : SearchEvent()
    data class OnMovieClicked(val id:Int) : SearchEvent()

}

sealed class SearchEffect {
    data object NavigateBack : SearchEffect()
    data class NavigateDetail(val id :Int):SearchEffect()
}
