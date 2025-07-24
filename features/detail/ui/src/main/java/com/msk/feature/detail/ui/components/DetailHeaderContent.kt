package com.msk.feature.detail.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.msk.design_system.components.CineJetText
import com.msk.design_system.components.IconWithText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.detail.ui.R
import com.msk.model.detail.MovieDetail

@Composable
fun DetailHeaderContent(moviedetail: MovieDetail) {

    Column {
        CineJetText(
            modifier = Modifier.padding(LocalCineJetSpacing.current.medium).align(CenterHorizontally),
            text = moviedetail.title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconWithText(
                    iconId = Icons.Default.DateRange,
                    text = moviedetail.releaseDate.year.toString()
                )
                IconWithText(iconId = R.drawable.time, text = moviedetail.runtime.toString())
                IconWithText(iconId = R.drawable.movie, text = moviedetail.genres.firstOrNull()?.name ?: "-")
            }
        }

    }

}