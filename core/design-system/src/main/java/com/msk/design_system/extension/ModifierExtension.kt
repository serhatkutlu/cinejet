package com.msk.design_system.extension

import androidx.compose.foundation.border
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

fun Modifier.carouselTransition(startValue : Float = 0.8F, page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation =
            lerp(
                start = startValue,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        alpha = transformation
        scaleY = transformation
    }

@Composable
 fun Modifier.customBorder(
    radius: Int = 18,
    color: Color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
    width: Dp = 2.dp
): Modifier {
    return this.border(
        width = width,
        color = color,
        shape = RoundedCornerShape(radius.dp)
    )
}
