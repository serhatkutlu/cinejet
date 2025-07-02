package com.msk.feature.detail.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.msk.design_system.components.CineJetText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.detail.ui.DetailUiState
import com.msk.feature.detail.ui.util.Constants

@Composable
fun DetailTabContent(detailUiState: DetailUiState) {
    val detail = detailUiState.movieDetail

    if (detailUiState.isLoading) {
        CircularProgressIndicator()
    } else if (detail != null) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            if (detail.overview.isNotBlank()){

                CineJetText(
                    modifier = Modifier.padding(LocalCineJetSpacing.current.medium),
                    text = Constants.OVER_VIEW,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(LocalCineJetSpacing.current.extraSmall))
                CineJetText(
                    modifier = Modifier.padding(LocalCineJetSpacing.current.medium),
                    text = detail.overview,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.height(LocalCineJetSpacing.current.extraSmall))

            CastRowContent(detail.casts)
        }

    } else {
        CineJetText(text = "No detail found.")
    }
}


