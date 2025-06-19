package com.msk.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msk.common.util.getPosterUrl
import com.msk.design_system.extension.carouselTransition
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.Movie


@Composable
fun CineJetCarousel(
    modifier: Modifier,
    list: List<Movie>,
    contentPadding: PaddingValues= PaddingValues(LocalCineJetSpacing.current.extraLarge),
    pageSpacing: Dp=LocalCineJetSpacing.current.extraMedium
) {

    val pagerState= rememberPagerState(pageCount = { list.size })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = contentPadding,
        pageSpacing = pageSpacing
    ) {page->

        val movie = list[page]
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .carouselTransition(page = page, pagerState = pagerState)
                .clip(RoundedCornerShape(LocalCineJetSpacing.current.extraSmall))
        ) {
            Box{
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = getPosterUrl(movie.backdropPath) ,
                contentScale = ContentScale.FillBounds,
                contentDescription = movie.title
            )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                startY = 100f
                            )
                        )
                )
            CineJetText(Modifier.align(Alignment.BottomStart).padding(LocalCineJetSpacing.current.medium),movie.title, style = MaterialTheme.typography.headlineSmall, color = Color.LightGray)
        }}

    }
}
