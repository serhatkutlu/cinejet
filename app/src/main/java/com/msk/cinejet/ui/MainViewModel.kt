package com.msk.cinejet.ui


import androidx.lifecycle.viewModelScope
import com.msk.cinejet.ui.navigation.CineJetTopLevelNavigation
import com.msk.design_system.base.viewmodel.BaseViewModel
import com.msk.design_system.navigation.Screen
import com.msk.design_system.util.ThemePreference
import com.msk.model.common.MediaType
import com.msk.model.settings.AppLanguage
import com.msk.preferences.UserPreferencesDataStore
import com.msk.ui.navigation.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val userPreferencesDataSource: UserPreferencesDataStore) :
    BaseViewModel<UiState, UiEvent, UiEffect>(UiState()) {




    val themePreference: StateFlow<ThemePreference> = userPreferencesDataSource.themeMode
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ThemePreference.Dynamic)

    val appLanguage: SharedFlow<AppLanguage?> = userPreferencesDataSource.language
        .distinctUntilChanged()
        .drop(1)
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5_000))




    private var isShowedInternetDialog: Boolean = false
    override fun onEvent(event: UiEvent) {
        when (event) {

            is UiEvent.MovieIdChangedEvent -> updateState {
                copy(
                    expandedScreenState =
                        ExpandedScreenState.Detail(event.movieId)
                )
            }

            is UiEvent.SeeAllMediaClicked -> updateState {
                copy(
                    expandedScreenState =
                        ExpandedScreenState.SeeAll(event.mediaType)
                )
            }

            is UiEvent.ShowAlertDialog -> {
                if (!isShowedInternetDialog) {
                    isShowedInternetDialog = true
                    setEffect {
                        UiEffect.ShowAlertDialog(event.message)
                    }
                }
            }
        }
    }

    private fun isTopLevelDestination(route: String): Boolean {
        return CineJetTopLevelNavigation.entries.any { it.route.route == route }
    }
}

data class UiState(
    val expandedScreenState: ExpandedScreenState = ExpandedScreenState.Detail(),
    val lastTopLevelRoute: Screen = HomeScreen,

)

sealed class ExpandedScreenState() {
    data class Detail(val mediaId: Int? = null) : ExpandedScreenState()
    data class SeeAll(val category: MediaType) : ExpandedScreenState()
}


sealed class UiEvent {
    data class MovieIdChangedEvent(val movieId: Int) : UiEvent()
    data class SeeAllMediaClicked(val mediaType: MediaType) : UiEvent()
    data class ShowAlertDialog(val message: String) : UiEvent()
}

sealed class UiEffect {
    data class ShowAlertDialog(val message: String) : UiEffect()
}

