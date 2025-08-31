package com.msk.feature.favorites.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.favorites.ui.FavoritesRoute
import kotlinx.serialization.Serializable

@Serializable
data object FavoritesScreen : Screen {
    override val route = "FavoritesScreen"

}


fun NavGraphBuilder.favoriteGraph(modifier: Modifier = Modifier,onNavigateToDetail: (Int)->Unit) {
    composable<FavoritesScreen> {
        FavoritesRoute(modifier = modifier, onNavigateToDetail =onNavigateToDetail )
    }
}