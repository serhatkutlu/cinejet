package com.msk.cinejet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.msk.cinejet.ui.navigation.CineJetBottomBar
import com.msk.cinejet.ui.navigation.CineJetNavGraph
import com.msk.cinejet.ui.navigation.CineJetNavigationRail
import com.msk.cinejet.ui.navigation.CineJetNavigationWrapper
import com.msk.cinejet.ui.navigation.CineJetTopLevelNavigation
import com.msk.cinejet.ui.navigation.CinejetNavigationWrapper
import com.msk.cinejet.ui.testScreen.DetailRoute
import com.msk.cinejet.ui.testScreen.SeeAllRoute
import com.msk.ui.HomeRoute


@Composable
internal fun CineJetMain() {
    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentUiState = uiState
    val cineJetAppState = rememberCineJetAppState()
    val currentTopLevelDestination = cineJetAppState.currentTopLevelDestination

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT ||
                adaptiveInfo.windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT



    val navLayoutType = when {
        isCompact -> NavigationSuiteType.NavigationBar
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationRail
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationDrawer
        else -> NavigationSuiteType.NavigationBar
    }


    CineJetNavigationWrapper(navLayoutType = navLayoutType, cineJetBottomBar = {
        CineJetBottomBar(
            CineJetTopLevelNavigation.entries,
            currentTopLevelDestination
        ) { cineJetAppState.navigate(it.route) }
    }, cineJetNavigationRail = {
        CineJetNavigationRail(
            modifier =  Modifier.width(80.dp).padding(top = 20.dp),
            topLevelDestinations = CineJetTopLevelNavigation.entries,
            currentDestination = currentTopLevelDestination?.route,
            onNavigate = cineJetAppState::navigate
        )
    }) {
        if (isCompact) {
            CineJetNavGraph(appState = cineJetAppState)
        } else {
            Box(Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.navigationBars.union(
                            WindowInsets.statusBars
                        )
                    )
                ) {
                    HomeRoute(
                        Modifier
                            .weight(1f),
                        onMovieSelected = { id ->
                            viewModel.onEvent(UiEvent.OnFirstMovieLoaded(id))
                        },
                        onSeeAllClick = { mediaType ->
                            viewModel.onEvent(
                                UiEvent.OnSeeAllClicked(mediaType)
                            )
                        })
                    when (currentUiState) {
                        is ExpandedScreenState.Detail -> {
                            DetailRoute(
                                Modifier
                                    .weight(1f)
                                    .background(color = Color.Red),
                                id = currentUiState.mediaId
                            )
                        }

                        is ExpandedScreenState.SeeAll -> {
                            SeeAllRoute(
                                Modifier
                                    .weight(1f)
                                    .background(color = Color.Blue),
                                mediaType = currentUiState.category
                            )
                        }

                    }

                }
            }
        }
    }
}