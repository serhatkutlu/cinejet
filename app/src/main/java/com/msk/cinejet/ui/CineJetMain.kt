package com.msk.cinejet.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.msk.cinejet.ui.navigation.CineJetTwoPaneScreen
import com.msk.design_system.components.CineJetErrorDialog


@Composable
internal fun CineJetMain( ) {
    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentUiState = uiState
    val cineJetAppState = rememberCineJetAppState()

    val currentTopLevelDestination = cineJetAppState.currentTopLevelDestination

    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }


    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT ||
                adaptiveInfo.windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT


    val isTwoPane =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED


    val navLayoutType = when {
        isCompact -> NavigationSuiteType.NavigationBar
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationDrawer
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationDrawer
        else -> NavigationSuiteType.NavigationBar
    }

    LaunchedEffect(isTwoPane) {
        cineJetAppState.isCompact = !isTwoPane
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect{
            when(it){
                is UiEffect.ShowAlertDialog-> {
                    dialogMessage = it.message
                    showDialog = true
                }

            }

        }
    }

    if (showDialog){
        CineJetErrorDialog(dialogMessage, onDismiss = {showDialog=false})
    }



    CineJetNavigationWrapper(
        navLayoutType = navLayoutType,
        isBottomBarVisible = cineJetAppState.isBottomBarVisible,
        cineJetBottomBar = {
            CineJetBottomBar(
                CineJetTopLevelNavigation.entries,
                currentTopLevelDestination
            ) { cineJetAppState.navigate(it.route) }
        },
        cineJetNavigationRail = {
            CineJetNavigationRail(
                modifier = Modifier
                    .width(80.dp),
                topLevelDestinations = CineJetTopLevelNavigation.entries,
                currentDestination = currentTopLevelDestination?.route,
                onNavigate = cineJetAppState::navigate
            )
        }) {
        if (!isTwoPane) {

            CineJetNavGraph(
                appState = cineJetAppState,
                modifier = Modifier.fillMaxSize(),
                onUiStateChange = { viewModel.onEvent(it) }
            )
        } else {

            Box(Modifier.fillMaxSize()) {


                CineJetTwoPaneScreen(
                    appState = cineJetAppState,
                    modifier = Modifier,
                    currentUiState.expandedScreenState,
                    onUiStateChange = { viewModel.onEvent(it) }
                )

            }

        }
    }
}

