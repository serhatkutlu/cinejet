package com.msk.feature.detail.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.msk.common.util.parseImageUrl
import com.msk.design_system.components.CineJetAsyncImage
import com.msk.design_system.components.CineJetText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.model.detail.Cast

@Composable
fun CastRowContent(casts: List<Cast>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(LocalCineJetSpacing.current.medium)) {
        items(casts.size) {
            CastItem(casts[it])

        }
    }
}

@Composable
private fun CastItem(cast: Cast) {
    val formattedImageUrl = cast.profilePath?.parseImageUrl()
    Column {
        CineJetAsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .align(Alignment.CenterHorizontally), imageUrl = formattedImageUrl
        )
        CineJetText(
            text = cast.name,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelLarge
        )
        CineJetText(
            text = cast.character,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelSmall
        )

    }
}