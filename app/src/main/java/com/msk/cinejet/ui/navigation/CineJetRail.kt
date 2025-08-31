package com.msk.cinejet.ui.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msk.design_system.components.CineJetText
import com.msk.design_system.navigation.Screen
import com.msk.design_system.theme.LocalCineJetSpacing

//@Composable
//fun CineJetNavigationRail(
//    modifier: Modifier = Modifier,
//    topLevelDestinations: List<CineJetTopLevelNavigation>,
//    currentDestination: Screen?,
//    onNavigate: (Screen) -> Unit
//) {
//    NavigationRail(modifier = modifier) {
//        topLevelDestinations.forEach { destination ->
//            NavigationRailItem(
//                selected = currentDestination == destination.route,
//                onClick = { onNavigate(destination.route) },
//                icon = {
//                    val icon = if (destination.route == currentDestination) {
//                        destination.selectedIcon
//                    } else {
//                        destination.unselectedIcon
//                    }
//
//                    Icon(
//                        imageVector = icon,
//                        contentDescription = stringResource(destination.title)
//                    )
//                },
//                label = { CineJetText(text = stringResource(destination.title)) }
//            )
//        }
//    }
@Composable
fun CineJetNavigationRail(
    modifier: Modifier = Modifier,
    topLevelDestinations: List<CineJetTopLevelNavigation>,
    currentDestination: Screen?,
    onNavigate: (Screen) -> Unit
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            topLevelDestinations.forEach { destination ->
                Spacer(Modifier.padding(top = LocalCineJetSpacing.current.medium))
                NavigationRailItem(
                    selected = currentDestination == destination.route,
                    onClick = { onNavigate(destination.route) },
                    icon = {
                        val icon = if (destination.route == currentDestination) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = stringResource(destination.title)
                        )
                    },
                    label = { CineJetText(text = stringResource(destination.title)) }
                )
            }
        }
    }
}
