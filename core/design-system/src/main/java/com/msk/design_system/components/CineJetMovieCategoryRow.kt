package com.msk.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msk.common.util.parseImageUrl
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.design_system.util.Constants.SEE_ALL_BUTTON_TEXT
import com.msk.model.common.Movie
import kotlinx.coroutines.flow.Flow

@Composable
fun CineJetMovieCategoryRow(
    mediaType: String,
    onSeeAllClick: () -> Unit,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    LocalCineJetSpacing.current.medium,
                    vertical = LocalCineJetSpacing.current.small
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CineJetText(
                text = mediaType,
                style = MaterialTheme.typography.titleMedium
            )
            TextButton(onClick = onSeeAllClick) {
                CineJetText(
                    text = SEE_ALL_BUTTON_TEXT,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = LocalCineJetSpacing.current.medium),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies.size) { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.medium))
                }
                MovieCard(movie = movies[index], onClick = {
                    onMovieClick(movies[index]) })
            }
        }
    }
}


@Composable
 fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(140.dp, 200.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            ,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = movie.posterPath?.parseImageUrl(),
                contentScale = ContentScale.FillBounds,
                contentDescription = movie.title
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f)),
                            startY = 150f
                        )
                    )
            ) {

                CineJetText(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(LocalCineJetSpacing.current.medium),
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )

            }
            val rating= movie.voteAverage.toFloat()
            if (rating>0f){
                CineJetRatingIndicator(
                    rating = movie.voteAverage.toFloat(),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(LocalCineJetSpacing.current.small))
            }

        }

    }
}

@Composable
 fun MovieCard(title: String, posterPath: String?, voteAverage: Float, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(140.dp, 200.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick() }
            ,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = posterPath?.parseImageUrl(),
                contentScale = ContentScale.FillBounds,
                contentDescription = title
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f)),
                            startY = 150f
                        )
                    )
            ) {

                CineJetText(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(LocalCineJetSpacing.current.medium),
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )

            }
            if (voteAverage > 0f){
                CineJetRatingIndicator(
                    rating = voteAverage,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(LocalCineJetSpacing.current.small))
            }

        }

    }
}


