package com.msk.cinejet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.msk.cinejet.ui.CineJetAppState
import com.msk.feature.detail.ui.navigation.Detail
import com.msk.ui.navigation.Home
import com.msk.feature.detail.ui.navigation.detailScreen
import com.msk.ui.navigation.homeGraph


@Composable
fun CineJetNavGraph(
    modifier: Modifier = Modifier,
    appState: CineJetAppState,

) {
    NavHost(appState.navController, startDestination = Home) {
        homeGraph(onMovieSelected = {id->
            appState.navigate(Detail(id))
        })
        detailScreen()
//        composable(CineJetNavigationItem.SearchScreen.route.route) {
//            Box(Modifier.fillMaxSize()){
//                Text(text = "SearchScreen")
//            }
//        }
//        composable(CineJetNavigationItem.FavoritesScreen.route.route) {
//            Box(Modifier.fillMaxSize()){
//                Text(text = "FavoritesScreen")
//            }
//        }
//        composable(CineJetNavigationItem.SettingsScreen.route.route) {
//            Box(Modifier.fillMaxSize()){
//                Text(text = "SettingsScreen")
//            }
//        }

    }
}