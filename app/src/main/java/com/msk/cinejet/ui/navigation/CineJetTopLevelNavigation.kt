package com.msk.cinejet.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.msk.cinejet.R
import com.msk.design_system.navigation.Screen
import com.msk.feature.search.ui.navigation.SearchFlow

enum class CineJetTopLevelNavigation(
    val route: Screen,
    @StringRes val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)  {

    HomeScreen(
        route = com.msk.ui.navigation.HomeScreen,
        title = R.string.home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),

    SearchScreen(
        route = SearchFlow,
        title = R.string.search,
        selectedIcon = Icons.Outlined.Search,
        unselectedIcon = Icons.Outlined.Search,
    ),

    FavoritesScreen(
        route =com.msk.feature.favorites.ui.navigation.FavoritesScreen ,
        title = R.string.favorites,
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
    ),

    SettingsScreen(
        route = com.msk.feature.settings.ui.settings.navigation.SettingsScreen,
        title = R.string.settings,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
    );

}
