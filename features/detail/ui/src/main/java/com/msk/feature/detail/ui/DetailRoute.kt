package com.msk.feature.detail.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

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
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tabTitles = MovieDetailTab.fromResString()


    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is DetailUiEffect.NavigateToDetail -> {
                    navigateToDetail(it.movieId)
                }
            }
        }
    }

    LaunchedEffect(id) {
        id.let {
            viewModel.onEvent(DetailUiEvent.OnMovieIdChanged(it))
        }
    }
    DetailScreen(
        modifier = modifier,
        uiState = uiState,
        onTabSelected = { index ->
            viewModel.onEvent(DetailUiEvent.OnTabSelected(index,id))
        },
        tabTitles = tabTitles,
        onRecommendationClick = { id ->
            viewModel.onEvent(DetailUiEvent.OnRecommendationClick(id))
        }
    )
}


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    onTabSelected: (Int) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    tabTitles: List<String> = listOf(),
    onRecommendationClick: (Int) -> Unit = {}
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
                modifier = Modifier.aspectRatio(1f),
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> DetailTabContent(
                        uiState,
                        onRecommendationClick = onRecommendationClick
                    )

                    1 -> VideoTabContent(uiState)
                    2 -> ReviewTabContent(uiState)
                }
            }
            Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.large))
        }
    }
}







