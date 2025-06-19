package com.msk.cinejet.ui.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.msk.design_system.components.CineJetText
import com.msk.design_system.navigation.Screen

@Composable
fun CineJetNavigationRail(
    modifier: Modifier = Modifier,
    topLevelDestinations: List<CineJetTopLevelNavigation>,
    currentDestination: Screen?,
    onNavigate: (Screen) -> Unit
) {
    NavigationRail(modifier) {
        topLevelDestinations.forEach { destination ->
            NavigationRailItem(
                selected = currentDestination == destination.route,
                onClick = { onNavigate(destination.route) },
                icon = {val icon =if (destination.route == currentDestination) {
                    destination.selectedIcon
                } else {
                    destination.unselectedIcon
                }

                    Icon(imageVector = icon, contentDescription = stringResource(destination.title)) },
                label = { CineJetText(text = stringResource(destination.title)) }
            )
        }
    }
}