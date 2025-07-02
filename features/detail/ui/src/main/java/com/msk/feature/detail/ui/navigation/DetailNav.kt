package com.msk.feature.detail.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.detail.ui.DetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class Detail(val id: Int) : Screen {
    override val route = "Detail"
}


fun NavGraphBuilder.detailScreen() {
    composable<Detail>{
        DetailRoute(
            modifier = Modifier
        )
    }
}