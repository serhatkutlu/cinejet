package com.msk.feature.search.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.search.ui.explore.ExploreRoute
import com.msk.model.common.MediaType
import kotlinx.serialization.Serializable

@Serializable
object Explore : Screen {
    override val route: String = "search"
}


fun NavGraphBuilder.exploreGraph(
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    onNavigateToSearch: () -> Unit,
    modifier: Modifier
) {
    composable<Explore> {
        ExploreRoute(
            modifier = modifier,
            onMovieSelected = onMovieSelected,
            onSeeAllClick = onSeeAllClick,
            onNavigateToSearch = onNavigateToSearch
        )
    }

}