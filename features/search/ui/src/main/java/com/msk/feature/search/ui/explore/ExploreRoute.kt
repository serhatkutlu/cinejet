package com.msk.feature.search.ui.explore


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.msk.design_system.components.CineJetMovieCategoryRow
import com.msk.design_system.components.CineJetText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.search.ui.ClickableSearchTextField
import com.msk.model.common.MediaType


@Composable
fun ExploreRoute(
    modifier: Modifier = Modifier,
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    onNavigateToSearch: () -> Unit = { }
) {
    val viewModel = hiltViewModel<ExploreViewModel>()

    //val mediaTypeMovies by viewModel.mediaTypeMovies.collectAsState()
    val uiState by viewModel.uiState.collectAsState()



    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is ExploreEffect.ClickSearch -> onNavigateToSearch()
                is ExploreEffect.NavigateToSeeAll-> onSeeAllClick(effect.mediaType)
                is ExploreEffect.NavigateToMovieDetails -> onMovieSelected(effect.movieId)
            }
        }
    }
    ExploreScreen(
        uiState=uiState,
        onMovieSelected = {viewModel.onEvent(ExploreEvent.OnMovieSelected(it))},
        onSeeAllClick = {viewModel.onEvent(ExploreEvent.OnSeeAllClick(it))},
        modifier = modifier,
        onClickSearch = {viewModel.onEvent(ExploreEvent.OnClickSearch)},
        onPullToRefresh = {viewModel.onEvent(ExploreEvent.OnPullToRefresh)}
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExploreScreen(
    uiState: ExploreState,
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = Search,
    onClickSearch: () -> Unit = { },
    onPullToRefresh: () -> Unit = { }
) {

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = onPullToRefresh,
        state = rememberPullToRefreshState(),
    ) {
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            CenterAlignedTopAppBar(
                title = { CineJetText("Search", style = MaterialTheme.typography.headlineMedium) },

                )

            ClickableSearchTextField(
                placeholder = placeholder,
                onClick = onClickSearch
            )

            Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.large))

            uiState.movies.forEach { (mediaType, movies) ->
                CineJetMovieCategoryRow(
                    mediaType = mediaType.mediaType,
                    onSeeAllClick = { onSeeAllClick(mediaType) },
                    movies = movies,
                    onMovieClick = { onMovieSelected(it.id) }

                )
            }
        }
    }
}


private const val Search = "Search"