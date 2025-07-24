package com.msk.feature.search.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.msk.design_system.components.MovieCard
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.search.ui.FullScreenSearch
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.flowOf


@Composable
fun SearchRoute(modifier: Modifier = Modifier, onBack: () -> Unit,navigateToDetail:(Int) -> Unit ) {


    val viewModel = hiltViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val query = uiState.query
    val results = remember(uiState.results) {
        flowOf(uiState.results)
    }.collectAsLazyPagingItems()


    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect {
            when (it) {
                is SearchEffect.NavigateBack -> {
                    onBack()
                }

                is SearchEffect.NavigateDetail -> {
                    navigateToDetail(it.id)
                }
            }
        }
    }

    SearchScreen(
        modifier = modifier,
        query = query,
        onQueryChange = { viewModel.onEvent(SearchEvent.OnQueryChanged(it)) },
        results = results,
        onBack = { viewModel.onEvent(SearchEvent.OnBackPressed)
        },
        onMovieClick = {viewModel.onEvent(SearchEvent.OnMovieClicked(id = it))}
    )
}

@Composable
private fun SearchScreen(
    modifier: Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    results: LazyPagingItems<Movie>,
    onBack: () -> Unit = {},
    onMovieClick: (Int) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val scrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                focusManager.clearFocus()
                return Offset.Zero
            }
        }
    }



    Column {
        FullScreenSearch(
            query = query,
            onQueryChange = onQueryChange,
            onBack = onBack,
            modifier = modifier
        )

        Spacer(Modifier.height(LocalCineJetSpacing.current.medium))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollConnection),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            items(results.itemCount) { item ->
                val movie = results[item]
                movie?.let {
                    MovieCard(
                        movie = it,
                        onClick = { onMovieClick(it.id) }
                    )
                }

            }
        }
    }
}
