package com.msk.cinejet.ui.navigation


import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable



@Composable
fun CineJetNavigationWrapper(
    navLayoutType: NavigationSuiteType ,
    cineJetBottomBar: @Composable () -> Unit,
    cineJetNavigationRail: @Composable () -> Unit,
    content: @Composable () -> Unit
) {


    NavigationSuiteScaffoldLayout(navigationSuite = {
        when (navLayoutType) {
            NavigationSuiteType.NavigationBar -> cineJetBottomBar()
            NavigationSuiteType.NavigationRail, NavigationSuiteType.NavigationDrawer -> cineJetNavigationRail()
            else -> cineJetBottomBar()
        }

    }
    ) {
        Surface {
            content()
        }

    }
}

