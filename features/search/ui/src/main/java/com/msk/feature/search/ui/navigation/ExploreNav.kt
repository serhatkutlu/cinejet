package com.msk.feature.search.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.common.util.MediaType
import com.msk.design_system.navigation.Screen
import com.msk.feature.search.ui.explore.ExploreRoute
import kotlinx.serialization.Serializable

@Serializable
object Explore : Screen {
    override val route: String = "search"
}


fun NavGraphBuilder.exploreGraph(
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    composable<Explore> {
        ExploreRoute(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            onMovieSelected = onMovieSelected,
            onSeeAllClick = onSeeAllClick,
            onNavigateToSearch = onNavigateToSearch
        )
    }

}