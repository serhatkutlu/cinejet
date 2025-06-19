package com.msk.ui.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.ui.HomeRoute

import kotlinx.serialization.Serializable


@Serializable
data object Home : Screen {
    override val route = "Home"
}


fun NavGraphBuilder.homeGraph(onMovieSelected: (Int) -> Unit) {
    composable<Home>(

    ) {
        HomeRoute(
            modifier = Modifier,
            onMovieSelected = onMovieSelected,
            onSeeAllClick = {

            }
        )
    }
}

@Serializable
data object Search : Screen {
    override val route = "Search"
}


fun NavGraphBuilder.searchScreen(
) {
    composable<Search>(

    ) {
        //val viewModel = hiltViewModel<HomeViewModel>()
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//        val uiEffect = viewModel.uiEffect
        SearchScreen(
//            uiState = uiState,
//            uiEffect = uiEffect,
//            onAction = viewModel::onAction,
//            onNavigateSearch = onNavigateSearch,
//            onNavigateDetail = onNavigateDetail,
//            onNavigateCategory = onNavigateCategory
        )
    }
}

@Composable
fun SearchScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black))
}


