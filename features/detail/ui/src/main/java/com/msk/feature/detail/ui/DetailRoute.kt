package com.msk.feature.detail.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.msk.design_system.components.CineJetText
import com.msk.design_system.extension.customBorder

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
    navigateToDetail: (Int) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val tabTitles = MovieDetailTab.fromResString()


    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is DetailUiEffect.NavigateToDetail -> {
                    navigateToDetail(it.movieId)
                }

                DetailUiEffect.NavigateBack -> onBackPressed()
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
        },
        onBackPressed = {
            viewModel.onEvent(DetailUiEvent.OnBackClick)
        },
        onClickFavorite = { id , isFavorite->
            viewModel.onEvent(DetailUiEvent.OnFavoriteClick(id,isFavorite))
        }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUiState,
    onTabSelected: (Int) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    tabTitles: List<String> = listOf(),
    onRecommendationClick: (Int) -> Unit = {},
    onBackPressed: () -> Unit = {},
    onClickFavorite: (Long,Boolean) -> Unit
) {
    val pagerState = rememberPagerState { tabTitles.size }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isFavorite = uiState.movieDetail?.isFavorite?: false
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor =Color.Transparent,
        scrolledContainerColor =  Color.Transparent,
    )

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = topAppBarColors,
                title = { }
                     , navigationIcon = {
                    IconButton(
                       onClick = onBackPressed,

                    ){
                        Icon( imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            modifier = Modifier.size(30.dp),
                            tint = Color.White,
                            contentDescription = stringResource(id = R.string.back),)
                    }
                },
                actions = {
                    IconButton(onClick = {onClickFavorite(uiState.movieDetail?.id?:0,!isFavorite) }) {
                        if (isFavorite){
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                tint = Color.Red,
                                contentDescription = stringResource(id = R.string.favorite)
                            )
                        }else{
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                tint = Color.Gray,
                                contentDescription = stringResource(id = R.string.favorite)
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
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

}







