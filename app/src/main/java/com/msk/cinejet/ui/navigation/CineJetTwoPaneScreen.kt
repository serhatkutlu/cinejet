package com.msk.cinejet.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.msk.cinejet.ui.CineJetAppState
import com.msk.cinejet.ui.ExpandedScreenState
import com.msk.cinejet.ui.UiEvent
import com.msk.feature.detail.ui.navigation.Detail
import com.msk.feature.detail.ui.navigation.detailGraph
import com.msk.feature.favorites.ui.navigation.favoriteGraph
import com.msk.feature.search.ui.navigation.SearchFlow
import com.msk.feature.search.ui.navigation.searchFlow
import com.msk.feature.seeall.ui.navigation.SeeAll
import com.msk.feature.seeall.ui.navigation.seeAllNavGraph
import com.msk.feature.settings.ui.settings.navigation.settingsGraph
import com.msk.ui.navigation.HomeScreen
import com.msk.ui.navigation.homeGraph

@Composable
fun CineJetTwoPaneScreen(
    appState: CineJetAppState,
    modifier: Modifier = Modifier,
    currentUiState: ExpandedScreenState,
    onUiStateChange: (UiEvent) -> Unit
) {
    Row(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {

            NavHost(
                navController = appState.primaryNavController,
                startDestination = HomeScreen,
            ) {
                homeGraph(
                    onMovieSelected = { id ->
                    onUiStateChange(UiEvent.MovieIdChangedEvent(id))
                    appState.navigate(Detail(id))
                }, onSeeAllClick = { mediaType ->
                    onUiStateChange(UiEvent.SeeAllMediaClicked(mediaType))
                    appState.navigate(
                        SeeAll(mediaType)
                    )
                }, modifier = modifier,
                    showAlertDialog = { message ->
                        onUiStateChange(UiEvent.ShowAlertDialog(message))
                    })
                favoriteGraph(onNavigateToDetail = { id ->
                    onUiStateChange(UiEvent.MovieIdChangedEvent(id))
                    appState.navigate(Detail(id))
                })
                searchFlow(
                    onMovieSelected = { id ->
                        onUiStateChange(UiEvent.MovieIdChangedEvent(id))
                        appState.navigate(Detail(id))
                    },
                    onSeeAllClick = {
                        onUiStateChange(UiEvent.SeeAllMediaClicked(it))
                        appState.navigate(SeeAll(it))
                    },
                    onNavigateToSearch = { appState.navigate(SearchFlow.Search) },
                    onBackClick = { appState.onPrimaryBackClick() },
                    modifier = modifier
                )
                settingsGraph()

            }
        }

        Spacer(
            Modifier
                .fillMaxHeight()
                .width(8.dp)
                .background(Color.LightGray.copy(0.5f))
        )

        Box(modifier = Modifier.weight(1f)) {
            val startDestination = when (currentUiState) {
                is ExpandedScreenState.Detail -> Detail(currentUiState.mediaId?: -1 )
                is ExpandedScreenState.SeeAll -> SeeAll(currentUiState.category)
            }
            NavHost(
                navController = appState.secondaryNavController,
                startDestination = startDestination
            ) {

                detailGraph(navigateToDetail = { id ->
                    onUiStateChange(UiEvent.MovieIdChangedEvent(id))
                    appState.navigate(Detail(id))
                }, onNavigateBack = {  }, modifier = modifier
                , isCompact = false)
                seeAllNavGraph(
                    onMovieSelected = { id ->
                        onUiStateChange(UiEvent.MovieIdChangedEvent(id))
                        appState.navigate(Detail(id))

                    }, modifier = modifier,
                    showErrorDialog = { onUiStateChange(UiEvent.ShowAlertDialog(it)) },
                    isCompact = false
                )
            }
        }

    }
}

