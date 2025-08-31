package com.msk.cinejet.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.msk.cinejet.ui.CineJetAppState
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
fun CineJetNavGraph(
    modifier: Modifier = Modifier,
    appState: CineJetAppState,
    onUiStateChange: (UiEvent) -> Unit = {},
) {

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = appState.compactNavController,
        startDestination = HomeScreen
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
        }, modifier = Modifier,
            showAlertDialog = { message ->
                onUiStateChange(UiEvent.ShowAlertDialog(message))
            }
        )


        detailGraph(navigateToDetail = { id ->
            onUiStateChange(UiEvent.MovieIdChangedEvent(id))
            appState.navigate(Detail(id))
        }, onNavigateBack = { appState.onBackClick() }, modifier = Modifier)

        seeAllNavGraph(
            onMovieSelected = { id ->
                onUiStateChange(UiEvent.MovieIdChangedEvent(id))
                appState.navigate(Detail(id))

            }, modifier = modifier,
            showErrorDialog = { message ->
                onUiStateChange(UiEvent.ShowAlertDialog(message))
            },
            onBackPressed = { appState.onBackClick() }
        )


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
            onBackClick = { appState.onBackClick() },
            modifier = modifier
        )

        favoriteGraph(onNavigateToDetail = { id ->
            onUiStateChange(UiEvent.MovieIdChangedEvent(id))
            appState.navigate(Detail(id))
        })

        settingsGraph()


    }
}