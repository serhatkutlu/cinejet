package com.msk.ui.navigation



import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.model.common.MediaType
import com.msk.ui.HomeRoute
import kotlinx.serialization.Serializable


@Serializable
data object Home : Screen {
    override val route = "Home"
}


fun NavGraphBuilder.homeGraph(onMovieSelected: (Int) -> Unit, onSeeAllClick: (MediaType) -> Unit, modifier: Modifier) {
    composable<Home>{

        HomeRoute(
            modifier = modifier,
            onMovieSelected = onMovieSelected,
            onSeeAllClick = onSeeAllClick
        )
    }
}
