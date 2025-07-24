package com.msk.cinejet.ui


import com.msk.common.util.MediaType
import com.msk.design_system.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
 class MainViewModel @Inject constructor() :
    BaseViewModel<ExpandedScreenState, UiEvent, UiEffect>(ExpandedScreenState.Detail()) {

    override fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnAdaptiveInfoChanged -> {
                updateState {
                    event.expandedScreenState
                }
            }

            is UiEvent.OnFirstMovieLoaded -> updateState {
                ExpandedScreenState.Detail(event.movieId)
            }
            is UiEvent.OnSeeAllClicked -> updateState {
                ExpandedScreenState.SeeAll(event.mediaType)
            }
        }
    }


}

sealed class ExpandedScreenState {
    data class Detail(val mediaId: Int?=null ) : ExpandedScreenState()
    data class SeeAll(val category: MediaType) : ExpandedScreenState()
}


 sealed class UiEvent {
    data class OnAdaptiveInfoChanged(val expandedScreenState: ExpandedScreenState) : UiEvent()
     data class OnFirstMovieLoaded(val movieId: Int) : UiEvent()
     data class OnSeeAllClicked(val mediaType: MediaType) : UiEvent()
}

 sealed class UiEffect {
}
