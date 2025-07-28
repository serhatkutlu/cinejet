package com.msk.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msk.design_system.components.CineJetCarousel
import com.msk.design_system.components.CineJetMovieCategoryRow
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.common.MediaType

@Composable
fun HomeRoute(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is UiEffect.ShowSnackBar -> {

                }
            }
        }

    }
    HomeScreen(
        modifier, onMovieSelected, onSeeAllClick, uiState,
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    onSeeAllClick: (MediaType) -> Unit,
    uiState: UiState,
) {
    Spacer(Modifier.height(LocalCineJetSpacing.current.large))
    LazyColumn(modifier) {
        uiState.movies?.let { movies ->

            movies.forEach { (mediaType, movieList) ->
                if (movieList == null) {
                    return@forEach
                }
                item {
                    if (mediaType == MediaType.TopRated) {
                        CineJetCarousel(
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 11f),
                            list = movieList
                        )
                    } else {
                        CineJetMovieCategoryRow(
                            mediaType = mediaType.name,
                            onSeeAllClick = { onSeeAllClick(mediaType) },
                            movies = movieList,
                            onMovieClick = {
                                onMovieSelected(it.id)
                            }
                        )
                    }
                    Spacer(Modifier.height(LocalCineJetSpacing.current.large))
                }
            }


        }
    }
}