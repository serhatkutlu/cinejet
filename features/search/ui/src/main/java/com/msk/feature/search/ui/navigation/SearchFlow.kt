package com.msk.feature.search.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.msk.common.util.MediaType
import com.msk.design_system.navigation.Screen
import kotlinx.serialization.Serializable


@Serializable
object SearchFlow : Screen {
    override val route: String = "search_flow"
}

fun NavGraphBuilder.searchFlow(
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    onNavigateToSearch: () -> Unit,
    onBackClick: () -> Unit
) {
    navigation<SearchFlow>(Explore) {
        exploreGraph(onMovieSelected, onSeeAllClick, onNavigateToSearch)
        searchGraph(
            onBackClick,
            navigateToDetail = onMovieSelected,
        )
    }
}