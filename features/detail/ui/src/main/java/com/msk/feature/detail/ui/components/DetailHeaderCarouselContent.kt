package com.msk.feature.detail.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.msk.common.util.TmdbPosterSize
import com.msk.common.util.parseImageUrl
import com.msk.design_system.components.CineJetAsyncImage
import com.msk.design_system.components.CineJetDetailCarousel
import com.msk.feature.detail.ui.DetailUiState
import com.msk.model.detail.MovieDetail

@Composable
fun DetailHeaderCarouselContent(movieDetail: MovieDetail?) {
    BoxWithConstraints {
        val height = maxWidth * 1.2f

        Box {
            if (movieDetail?.images?.posters.isNullOrEmpty()) {
                CineJetAsyncImage(

                    movieDetail?.backdropPath?.parseImageUrl(size = TmdbPosterSize.W500), Modifier
                        .height(height)
                        .fillMaxWidth()
                )
            } else {

                CineJetAsyncImage(
                    movieDetail?.backdropPath?.parseImageUrl(size = TmdbPosterSize.W92), Modifier
                        .height(height)
                        .fillMaxWidth()
                        .blur(2.dp)
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(height / 4)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
            CineJetDetailCarousel(
                Modifier
                    .height(height)
                    .fillMaxWidth(),
                list = movieDetail?.images?.posters ?: listOf()
            )

        }
    }
}