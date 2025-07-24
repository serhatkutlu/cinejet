package com.msk.feature.seeall.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msk.design_system.components.CineJetEmptyView
import com.msk.design_system.components.CineJetErrorDialog

import com.msk.design_system.components.CineJetText
import com.msk.design_system.components.MovieCard
import com.msk.design_system.components.MovieOverviewCard
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.common.Movie

@Composable
fun SeeAllRoute(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
) {
    val viewModel = hiltViewModel<SeeAllViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val mediaType = uiState.mediaType?.name ?: ""
    val movies = uiState.movies.collectAsLazyPagingItems()




    SeeAllScreen(
        modifier, onMovieSelected, mediaType, movies
    )
}

@Composable
internal fun SeeAllScreen(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    mediaType: String,
    movies: LazyPagingItems<Movie>
) {
    Column(modifier=modifier.fillMaxSize()) {
        CineJetText(
            modifier = Modifier.fillMaxWidth(),
            text = mediaType,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        PaginatedContentScreen(modifier, movies, onMovieSelected)

    }


}

@Composable

fun PaginatedContentScreen(modifier: Modifier,movies: LazyPagingItems<Movie>,onMovieSelected: (Int) -> Unit) {

    val isRefreshing = movies.loadState.refresh is LoadState.Loading
    val isAppending = movies.loadState.append is LoadState.Loading
    val refreshError = movies.loadState.refresh as? LoadState.Error
    val appendError = movies.loadState.append as? LoadState.Error


    Box(modifier = modifier.fillMaxSize()) {
        when {
            isRefreshing -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            refreshError != null -> {
                CineJetErrorDialog(message = refreshError.error.message ?: "Bir hata oluştu", onDismiss = { movies.retry() })
            }}
        when{


            movies.itemCount == 0 -> {

                CineJetEmptyView()
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(movies.itemCount) { index ->
                        movies[index]?.let { movie ->
                            MovieOverviewCard (movie = movie, onClick = { onMovieSelected(movie.id) })
                        }
                    }

                    item {
                        if (isAppending) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }

                        appendError?.let { error ->
                            CineJetErrorDialog (
                                message = error.error.message ?: "Daha fazla veri alınamadı",
                                onDismiss = { movies.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}


