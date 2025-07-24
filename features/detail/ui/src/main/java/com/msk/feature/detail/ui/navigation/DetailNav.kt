package com.msk.feature.detail.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msk.design_system.navigation.Screen
import com.msk.feature.detail.ui.DetailRoute
import kotlinx.serialization.Serializable

//@Serializable
//data class Detail(val id: Int) : Screen {
//    override val route: String
//        get() = "$baseRoute/$id"
//
//    companion object {
//        const val baseRoute = "Detail"
//        const val routeWithArg = "Detail/{id}"
//
//        fun createRoute(id: Int) = "$baseRoute/$id"
//    }
//}
@Serializable
data class Detail(val id: Int) : Screen {
    override val route: String
        get() = baseRoute

    companion object {
       private const val baseRoute = "Detail" }
}


fun NavGraphBuilder.detailGraph(navigateToDetail: (Int) -> Unit){
    composable<Detail>{backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id") ?: return@composable
        DetailRoute(
            id = id,
            modifier = Modifier,
            navigateToDetail = navigateToDetail
        )
    }
}


