package com.msk.feature.search.ui.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msk.design_system.navigation.Screen
import com.msk.feature.search.ui.search.SearchRoute
import kotlinx.serialization.Serializable



fun NavGraphBuilder.searchGraph(onBackClick: () -> Unit, navigateToDetail: (Int) -> Unit,modifier: Modifier) {
    composable<SearchFlow.Search> {
        SearchRoute(
            modifier =modifier ,
            onBackClick,
            navigateToDetail = navigateToDetail,
        )
    }
}