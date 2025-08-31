package com.msk.feature.seeall.ui

import PaginatedContent
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
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
import com.msk.model.common.MediaType
import com.msk.model.common.Movie



@Composable
fun SeeAllRoute(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    mediaType: MediaType,
    showErrorDialog: (String) -> Unit = {},
    onBackPressed:()->Unit ,
    isCompact:Boolean
) {
    val viewModel = hiltViewModel<SeeAllViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val movies = uiState.movies.collectAsLazyPagingItems()



    LaunchedEffect(mediaType) {
        viewModel.getMovies(mediaType)
    }
    LaunchedEffect(Unit){
        viewModel.uiEffect.collect{
            when(it){
                is UiEffect.AlertDialogEffect -> {
                  showErrorDialog(it.message)
                }
            }
        }
    }


    SeeAllScreen(
        modifier, onMovieSelected, mediaType.mediaType, movies, showErrorDialog ={
            viewModel.onEvent(UiEvent.AlertDialogEvent(it))
        },
        onBackPressed=onBackPressed,
        isCompact=isCompact
    )




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SeeAllScreen(
    modifier: Modifier,
    onMovieSelected: (Int) -> Unit,
    mediaType: String,
    movies: LazyPagingItems<Movie>,
    showErrorDialog:(String)->Unit = {},
    onBackPressed:()->Unit = {},
    isCompact:Boolean
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor =MaterialTheme.colorScheme.background,
        scrolledContainerColor =  MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
        navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
    )
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {

                    if (isCompact){
                        IconButton(
                            onClick = onBackPressed,

                            ){
                            Icon( imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                modifier = Modifier.size(30.dp),
                                contentDescription = stringResource(com.msk.core.design_system.R.string.back)
                            )
                        }
                    }
                },
                colors = topAppBarColors,
                title = { CineJetText(mediaType, style = MaterialTheme.typography.headlineSmall)}
                ,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(top = paddingValues.calculateTopPadding())) {

            PaginatedContent(
                items = movies,
                itemContent = { movie ->
                    MovieOverviewCard (movie = movie, onClick = { onMovieSelected(movie.id) })
                }
            , showErrorDialog = showErrorDialog)

        }
    }



}
