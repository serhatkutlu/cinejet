package com.msk.feature.settings.ui.settings.companents

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.msk.design_system.components.CineJetText

@Composable
fun <T> SegmentedControl(
    modifier: Modifier = Modifier,
    selected: T,
    options: List<T>,
    labelProvider: @Composable (T) -> String,
    onSelectedChange: (T) -> Unit
) {
    val selectedIndex = options.indexOfFirst { it == selected }

    val itemWidth = 80.dp
    val offsetX by animateDpAsState(
        targetValue = (selectedIndex * itemWidth),
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "offsetAnim"
    )

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(50))
            .padding(4.dp)
    ) {
        // Hareket eden highlight
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToPx(), 0) }
                .size(width = itemWidth, height = 40.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.primary)
        )

        // Seçenekler
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { option ->
                val isSelected = selected == option
                val textColor = if (isSelected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurfaceVariant

                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .height(40.dp)
                        .clip(RoundedCornerShape(50))
                        .clickable {
                            onSelectedChange(option)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    CineJetText(text = labelProvider(option), color = textColor)
                }
            }
        }
    }
}



