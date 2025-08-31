package com.msk.feature.settings.ui.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.settings.ui.settings.SettingsRoute
import kotlinx.serialization.Serializable

@Serializable
data object SettingsScreen:Screen{
    override val route: String
            get() = "SettingsScreen"

}


fun NavGraphBuilder.settingsGraph(){
    composable<SettingsScreen>{
        SettingsRoute()
    }
}