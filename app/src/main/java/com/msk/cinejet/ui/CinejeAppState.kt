package com.msk.cinejet.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.msk.cinejet.ui.navigation.CineJetTopLevelNavigation
import com.msk.design_system.navigation.Screen
import com.msk.feature.search.ui.navigation.SearchFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberCineJetAppState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    compactNavControler: NavHostController = rememberNavController(),
    primaryNavController: NavHostController = rememberNavController(),
    secondaryNavController: NavHostController = rememberNavController()
) = remember(
    compactNavControler,
    snackBarHostState,
    coroutineScope,
    primaryNavController,
    secondaryNavController
) {
    CineJetAppState(
        compactNavControler,
        primaryNavController,
        secondaryNavController,
        snackBarHostState,
        coroutineScope
    )
}

@Stable
class CineJetAppState(
    val compactNavController: NavHostController,
    val primaryNavController: NavHostController,
    val secondaryNavController: NavHostController,
    private val snackBarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,

    ) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)
    var isCompact = true

    private val currentCompactDestination: NavDestination?
        @Composable get() {
            val entry =
                compactNavController.currentBackStackEntryFlow.collectAsState(initial = null)
            return entry.value?.destination?.also { previousDestination.value = it }
                ?: previousDestination.value
        }

    private val currentExpandDestination: NavDestination?
        @Composable get(){
            val entry =
                primaryNavController.currentBackStackEntryFlow.collectAsState(initial = null)
            return entry.value?.destination?.also { previousDestination.value = it }
                ?: previousDestination.value
        }



    val currentTopLevelDestination: CineJetTopLevelNavigation?
        @Composable
        get() {
            val compactEntry = currentCompactDestination
            val expandEntry = currentExpandDestination

            val currentDestination =
                if (isCompact) compactEntry else expandEntry

            LaunchedEffect(currentDestination) {
                previousDestination.value = currentDestination
            }

            return remember(currentDestination, isCompact) {
                CineJetTopLevelNavigation.entries.firstOrNull { topLevel ->
                    val routeToCheck = if (isCompact && topLevel.route == SearchFlow) {
                        SearchFlow.Explore
                    } else {
                        topLevel.route
                    }

                    currentDestination?.route
                        ?.contains(routeToCheck.route.substringAfter('/')) ?: false
                }
            }
        }

    val isBottomBarVisible: Boolean
        @Composable get() = currentTopLevelDestination != null

    fun showSnackBar(message: String) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(message)
        }
    }




    fun onBackClick() = compactNavController.popBackStack()
    fun onPrimaryBackClick() = primaryNavController.popBackStack()


    fun navigate(route: Screen) {
        val isTopLevel =
            CineJetTopLevelNavigation.entries.any { route.route.startsWith(it.route.route) }

        if (isCompact) {
            val compactNavOption = if (isTopLevel) {

                navOptions {
                    popUpTo(compactNavController.graph.findStartDestination().id) {
                        saveState = true
                        inclusive=false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            } else {
                navOptions {
                    launchSingleTop = true
                }
            }

            compactNavController.navigate(route, compactNavOption)
        } else {

            val primaryNavOptions = navOptions {
                popUpTo(primaryNavController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            if (isTopLevel) {
                if (route == SearchFlow.Search) {
                    primaryNavController.navigate(route, navOptions {
                        launchSingleTop = true
                    })
                } else
                    primaryNavController.navigate(route, primaryNavOptions)
            } else secondaryNavController.navigate(route, navOptions {
                launchSingleTop = true
            })

        }
    }



}
