package com.msk.feature.detail.ui.navigation


import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.detail.ui.DetailRoute
import kotlinx.serialization.Serializable


@Serializable
data class Detail(val id: Int) : Screen {
    override val route: String
        get() = baseRoute

    companion object {
       private const val baseRoute = "Detail" }
}


fun NavGraphBuilder.detailGraph(navigateToDetail: (Int) -> Unit, onNavigateBack: () -> Unit, modifier: Modifier=Modifier,isCompact:Boolean=true){
    composable<Detail>{backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id") ?: return@composable

        DetailRoute(
            id = id,
            modifier = modifier,
            navigateToDetail = navigateToDetail,
            onBackPressed = onNavigateBack,
            isCompact = isCompact
        )
    }
}


