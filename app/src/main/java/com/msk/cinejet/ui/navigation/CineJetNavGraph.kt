package com.msk.cinejet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.msk.cinejet.ui.CineJetAppState
import com.msk.feature.detail.ui.navigation.Detail
import com.msk.ui.navigation.Home
import com.msk.feature.detail.ui.navigation.detailGraph
import com.msk.feature.search.ui.navigation.Search
import com.msk.feature.search.ui.navigation.exploreGraph
import com.msk.feature.search.ui.navigation.searchGraph
import com.msk.feature.search.ui.navigation.searchFlow
import com.msk.feature.seeall.ui.navigation.SeeAll
import com.msk.feature.seeall.ui.navigation.seeAllNavGraph
import com.msk.ui.navigation.homeGraph


@Composable
fun CineJetNavGraph(
    modifier: Modifier = Modifier,
    appState: CineJetAppState,

    ) {

    NavHost(appState.navController, startDestination = Home) {
        homeGraph(onMovieSelected = { id ->
            appState.navigate(Detail(id))
        }, onSeeAllClick = { mediaType ->
            appState.navigate(
                SeeAll(mediaType)
            )
        })


        detailGraph(navigateToDetail = { id ->
            appState.navigate(Detail(id))
        })

    seeAllNavGraph(
        onMovieSelected = { id ->
            appState.navigate(Detail(id))

        }
    )


        searchFlow(
            onMovieSelected = { id ->
                appState.navigate(Detail(id))
            },
            onSeeAllClick = { appState.navigate(SeeAll(it)) },
            onNavigateToSearch = { appState.navigate(Search) },
            onBackClick = { appState.onBackClick()}
        )



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