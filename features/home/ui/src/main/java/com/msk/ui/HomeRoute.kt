package com.msk.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msk.core.design_system.R
import com.msk.design_system.components.CineJetCarousel
import com.msk.design_system.components.CineJetMovieCategoryRow
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.common.MediaType

@Composable
fun HomeRoute(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    showAlertDialog: (String) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val offlineModeMessage = stringResource(id = R.string.offline_mode_message)

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is UiEffect.ShowSnackBar -> {

                }

                is UiEffect.ShowAlertDialog -> {
                    showAlertDialog(offlineModeMessage)
                }
            }
        }

    }


    HomeScreen(
        modifier,
        onMovieSelected,
        onSeeAllClick,
        onRefresh = { viewModel.onEvent(UiEvent.RefreshUi) },
        uiState,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    onRefresh: () -> Unit,
    uiState: UiState,
) {
    val allMoviesEmpty by remember(uiState.movies) {
        derivedStateOf {
            uiState.movies?.all { it.value.isNullOrEmpty() } == true
        }
    }

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = onRefresh,
        modifier = modifier,
        state = rememberPullToRefreshState(),
    ) {
        LazyColumn(modifier.fillMaxSize()) {
            when {
                allMoviesEmpty -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No movies found",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                else -> {
                    uiState.movies?.forEach { (mediaType, movieList) ->
                        if (movieList.isNullOrEmpty()) return@forEach

                        item {
                            if (mediaType == MediaType.TopRated) {
                                CineJetCarousel(
                                    Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 11f),
                                    list = movieList,
                                    onMovieClick = { onMovieSelected(it.id) }
                                )
                            } else {
                                CineJetMovieCategoryRow(
                                    mediaType = mediaType.name,
                                    onSeeAllClick = { onSeeAllClick(mediaType) },
                                    movies = movieList,
                                    onMovieClick = { onMovieSelected(it.id) }
                                )
                            }
                            Spacer(Modifier.height(LocalCineJetSpacing.current.large))
                        }
                    }
                }
            }
        }
    }
}
