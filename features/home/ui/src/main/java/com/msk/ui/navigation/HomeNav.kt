package com.msk.ui.navigation



import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.model.common.MediaType
import com.msk.ui.HomeRoute
import kotlinx.serialization.Serializable


@Serializable
data object HomeScreen : Screen {
    override val route = "HomeScreen"
}



fun NavGraphBuilder.homeGraph(onMovieSelected: (Int) -> Unit, onSeeAllClick: (MediaType) -> Unit, modifier: Modifier,showAlertDialog:(String)->Unit) {
    composable<HomeScreen>{

        HomeRoute(
            modifier = modifier,
            onMovieSelected = onMovieSelected,
            onSeeAllClick = onSeeAllClick,
            showAlertDialog = showAlertDialog
        )
    }
}
