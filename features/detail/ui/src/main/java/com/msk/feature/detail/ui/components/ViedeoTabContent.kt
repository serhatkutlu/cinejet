package com.msk.feature.detail.ui.components

import YouTubePlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.msk.design_system.components.CineJetText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.detail.ui.DetailUiState

@Composable
fun VideoTabContent(detailUiState: DetailUiState) {
    val videos = detailUiState.videos?.first()

    if (detailUiState.isLoading) {
        CircularProgressIndicator()
    } else if (videos != null) {
        val videoKey = videos.videoKey
        Column {
            CineJetText(text =videos.name , style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.large))
            YouTubePlayer(videoKey)

        }

    } else {
        Text("No videos found.")
    }
}


