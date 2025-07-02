package com.msk.design_system.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.msk.common.util.parseImageUrl
import com.msk.design_system.extension.carouselTransition
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.detail.ImageItem

@Composable
fun CineJetDetailCarousel(
    modifier: Modifier,
    list: List<ImageItem>,
    contentPadding: PaddingValues = PaddingValues(LocalCineJetSpacing.current.extraLarge),
    pageSpacing: Dp = LocalCineJetSpacing.current.extraMedium
) {

    val pagerState = rememberPagerState(pageCount = { list.size })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        contentPadding = contentPadding,
        pageSpacing = pageSpacing
    ) { page ->

        val imageItem = list[page]
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .carouselTransition(page = page, pagerState = pagerState)
                .clip(RoundedCornerShape(LocalCineJetSpacing.current.extraSmall))
        ) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = imageItem.filePath.parseImageUrl(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription =""
                )

            }
        }

    }
}
