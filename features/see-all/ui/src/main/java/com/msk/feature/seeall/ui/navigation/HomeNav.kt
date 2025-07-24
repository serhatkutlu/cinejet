package com.msk.feature.seeall.ui.navigation



import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.common.util.MediaType
import com.msk.design_system.navigation.Screen
import com.msk.feature.seeall.ui.SeeAllRoute

import kotlinx.serialization.Serializable


@Serializable
data class SeeAll(val mediaType:MediaType) : Screen {
    override val route = "SeeAll"
}


fun NavGraphBuilder.seeAllNavGraph(onMovieSelected: (Int) -> Unit) {
    composable<SeeAll>(

    ) {
        SeeAllRoute (
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            onMovieSelected = onMovieSelected,
        )
    }
}
