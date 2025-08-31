package com.msk.feature.seeall.ui.navigation



import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.msk.design_system.navigation.Screen
import com.msk.feature.seeall.ui.SeeAllRoute
import com.msk.model.common.MediaType
import kotlinx.serialization.Serializable


@Serializable
data class SeeAll(val mediaType: MediaType) : Screen {
    override val route = "SeeAll"
}


fun NavGraphBuilder.seeAllNavGraph(onMovieSelected: (Int) -> Unit,modifier: Modifier = Modifier,showErrorDialog: (String) -> Unit,onBackPressed: () -> Unit={},isCompact:Boolean=true){
    composable<SeeAll>{backStackEntry ->
        val mediaType=backStackEntry.arguments?.getSerializable("mediaType") as MediaType

        SeeAllRoute (
            modifier =modifier,
            onMovieSelected = onMovieSelected,
            mediaType,
            showErrorDialog = showErrorDialog,
            onBackPressed=onBackPressed,
            isCompact=isCompact
        )
    }
}
