package com.msk.feature.search.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.search.ui.search.SearchRoute
import kotlinx.serialization.Serializable

@Serializable
object Search : Screen {
    override val route: String
        get() = "Search_Screen"
}


fun NavGraphBuilder.searchGraph(onBackClick: () -> Unit, navigateToDetail: (Int) -> Unit) {
    composable<Search> {
        SearchRoute(
            Modifier.windowInsetsPadding(WindowInsets.statusBars),
            onBackClick,
            navigateToDetail = navigateToDetail
        )
    }
}