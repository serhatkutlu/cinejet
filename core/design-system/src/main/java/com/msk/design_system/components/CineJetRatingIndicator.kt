package com.msk.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.msk.design_system.theme.LocalCineJetSpacing
import java.util.Locale

@Composable
fun CineJetRatingIndicator(
    modifier: Modifier = Modifier,
    rating: Float, //
    maxRating: Float = 10f,
    size: Dp = 40.dp,
    strokeWidth: Dp = 2.dp,
) {
    val progress = (rating / maxRating).coerceIn(0f, 1f)
    val progressIndicatorColor = progress.let {
        when {
            it >= 0.7 -> Color(0xFF4CAF50)
            it >= 0.5 -> Color(0xFFFFC107)
            else -> Color(0xFFF44336)
        }}
        Box(

            contentAlignment = Alignment.Center,
            modifier = modifier.size(size).clip(MaterialTheme.shapes.medium).background(Color.DarkGray.copy(0.65f)).padding(
                LocalCineJetSpacing.current.extraSmall)
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
                color = progressIndicatorColor,
                strokeWidth = strokeWidth,
                trackColor = ProgressIndicatorDefaults.circularTrackColor,
            )
            val ratingText = String.format(Locale.US, "%.1f", rating)

            CineJetText(
                text =ratingText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

        }

}