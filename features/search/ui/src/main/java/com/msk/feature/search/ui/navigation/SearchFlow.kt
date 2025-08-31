package com.msk.feature.search.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.msk.design_system.navigation.Screen
import com.msk.model.common.MediaType
import kotlinx.serialization.Serializable


@Serializable
object SearchFlow : Screen {
    override val route: String = "SearchFlow"

    @Serializable
    object Explore : Screen {
        override val route: String = "${SearchFlow.route}/Explore"
    }

    @Serializable
    object Search : Screen {
        override val route: String = "${SearchFlow.route}/Search"
    }
}

fun NavGraphBuilder.searchFlow(
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    onNavigateToSearch: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier
) {
    navigation<SearchFlow>(SearchFlow.Explore) {
        exploreGraph(onMovieSelected, onSeeAllClick, onNavigateToSearch,modifier)
        searchGraph(
            onBackClick,
            navigateToDetail = onMovieSelected,
            modifier = Modifier
        )
    }
}