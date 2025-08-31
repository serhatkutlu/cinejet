package com.msk.feature.favorites.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msk.design_system.components.CineJetText
import com.msk.design_system.components.MovieOverviewCard
import com.msk.design_system.components.SwipeToDeleteItem
import com.msk.model.detail.MovieDetail

@Composable
fun FavoritesRoute(
    modifier: Modifier = Modifier,
    onNavigateToDetail: (Int) -> Unit,
) {

    val viewModel = hiltViewModel<FavoritesViewmodel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movies = uiState.movies




    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is UiEffect.NavigateToDetail -> {
                    onNavigateToDetail(it.id.toInt())
                }
            }
        }
    }

    FavoritesScreen(
        modifier = modifier,
        movies = movies,
        onClick = { viewModel.onEvent(UiEvent.OnMovieClicked(it)) },
        onDelete = { viewModel.onEvent(UiEvent.OnMovieFavoriteStatusChanged(movieId =it, isFavorite = false)) })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    movies: List<MovieDetail>,
    onClick: (Long) -> Unit,
    onDelete: (Long) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        scrolledContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
    )

    var swipedItemId by  remember { mutableStateOf<Long?>(null) }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors,
                title = {
                    CineJetText(
                        stringResource(R.string.favorites_title),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(top = paddingValues.calculateTopPadding())) {

            LazyColumn {
                items(movies.size, key = {
                    movies[it].id
                }){index ->
                    val item = movies[index]


                    SwipeToDeleteItem(
                        modifier=Modifier.padding(20.dp),
                        item = item,
                        itemId = item.id,
                      onDelete = { onDelete(item.id) },
                        swipedItemId=swipedItemId,
                        onItemSwiped = {swipedItemId= it}
                    ){
                        MovieOverviewCard(
                            posterPath = item.posterPath!!,
                            title = item.title,
                            overview = item.overview,
                            voteAverage = item.voteAverage,
                            onClick = { onClick(item.id) }
                        )
                    }
                }

            }


        }
    }


}

