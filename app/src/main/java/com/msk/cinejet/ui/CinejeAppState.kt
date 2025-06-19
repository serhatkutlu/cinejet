package com.msk.cinejet.ui

import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.rememberNavController
import com.msk.cinejet.ui.navigation.CineJetTopLevelNavigation
import com.msk.design_system.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberCineJetAppState(
navController: NavHostController= rememberNavController(),
snackBarHostState: SnackbarHostState= remember { SnackbarHostState() },
coroutineScope: CoroutineScope= rememberCoroutineScope(),
)= remember(navController, snackBarHostState, coroutineScope) {
CineJetAppState(navController, snackBarHostState, coroutineScope)
}
@Stable
class CineJetAppState(
    val navController: NavHostController,
    private val snackBarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    private val currentDestination: NavDestination?
        @Composable get() {
            val entry = navController.currentBackStackEntryFlow.collectAsState(initial = null)
            return entry.value?.destination?.also { previousDestination.value = it }
                ?: previousDestination.value
        }


    val currentTopLevelDestination: CineJetTopLevelNavigation?
        @Composable get() = CineJetTopLevelNavigation.entries.firstOrNull { topLevel ->
            currentDestination?.hasRoute(route = topLevel.route::class) == true

        }

    fun showSnackBar(message: String) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(message)
        }
    }

    fun onBackClick() = navController.popBackStack()
    fun navigate(route: Screen) {
        val isTopLevel = CineJetTopLevelNavigation.entries.any { route.route.startsWith(it.route.route) }

        val options = if (isTopLevel) {
            navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navOptions {
                launchSingleTop = true
            }
        }

        navController.navigate(route, options)
    }
}
