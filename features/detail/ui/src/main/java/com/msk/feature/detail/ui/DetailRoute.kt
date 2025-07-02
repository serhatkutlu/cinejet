package com.msk.feature.detail.ui

import YouTubePlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.msk.design_system.components.CineJetText

import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.detail.ui.components.DetailHeaderCarouselContent
import com.msk.feature.detail.ui.components.DetailHeaderContent
import com.msk.feature.detail.ui.components.DetailTabContent
import com.msk.feature.detail.ui.components.ReviewTabContent
import com.msk.feature.detail.ui.components.VideoTabContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun DetailRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tabTitles = MovieDetailTab.fromResString()


    DetailScreen(
        modifier = modifier,
        uiState = uiState,
        onTabSelected = { index ->
            viewModel.onEvent(DetailUiEvent.OnTabSelected(index))
        },
        tabTitles = tabTitles
    )
}


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    onTabSelected: (Int) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    tabTitles: List<String> = listOf()
) {
    val pagerState = rememberPagerState { tabTitles.size }

    LazyColumn(modifier = modifier) {
        item { DetailHeaderCarouselContent(uiState.movieDetail) }
        uiState.movieDetail?.let {
            item { DetailHeaderContent(it) }
        }
        item {
            TabRow(
                selectedTabIndex = pagerState.currentPage
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            onTabSelected(index)
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { CineJetText(text = title) }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.aspectRatio(1f)
            ) { page ->
                when (page) {
                    0 -> DetailTabContent(uiState)
                    1 -> VideoTabContent(uiState)
                    2 -> ReviewTabContent(uiState)
                }
            }
            Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.large))
        }
    }
}







