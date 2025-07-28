package com.msk.feature.seeall.ui.navigation



import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.seeall.ui.SeeAllRoute
import com.msk.model.common.MediaType
import kotlinx.serialization.Serializable


@Serializable
data class SeeAll(val mediaType: MediaType) : Screen {
    override val route = "SeeAll"
}


fun NavGraphBuilder.seeAllNavGraph(onMovieSelected: (Int) -> Unit,modifier: Modifier = Modifier) {
    composable<SeeAll>(

    ) {
        SeeAllRoute (
            modifier =modifier,
            onMovieSelected = onMovieSelected,
        )
    }
}
