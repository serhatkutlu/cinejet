package com.msk.feature.detail.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.msk.common.util.Constants.AVATAR_SHAPE_VALUE
import com.msk.common.util.TmdbPosterSize
import com.msk.common.util.parseImageUrl
import com.msk.design_system.components.CineJetAsyncImage
import com.msk.design_system.components.CineJetText
import com.msk.design_system.theme.LocalCineJetSpacing
import com.msk.feature.detail.ui.DetailUiState
import com.msk.model.detail.Review


@Composable
fun ReviewTabContent(detailUiState: DetailUiState) {
    val reviews = detailUiState.reviews.collectAsLazyPagingItems()

    val isRefreshing = reviews.loadState.refresh is LoadState.Loading
    val isAppending = reviews.loadState.append is LoadState.Loading
    val error = when {
        reviews.loadState.refresh is LoadState.Error -> (reviews.loadState.refresh as LoadState.Error).error
        reviews.loadState.append is LoadState.Error -> (reviews.loadState.append as LoadState.Error).error
        else -> null
    }

    when {
        isRefreshing -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CineJetText(text = error.localizedMessage ?: "Unknown error", color = Color.Red)
            }
        }

        else -> {
            LazyColumn(Modifier.fillMaxSize().padding(vertical = LocalCineJetSpacing.current.medium)) {
                items(count = reviews.itemCount) { index ->
                    val review = reviews[index]
                    review?.let {
                        ReviewRow(it)
                    }
                }

                if (isAppending) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ReviewRow(review: Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalCineJetSpacing.current.medium,
            )
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(LocalCineJetSpacing.current.smallMedium)
        ) {
            CineJetAsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                imageUrl = review.authorDetails.avatarPath
                    ?.parseImageUrl(TmdbPosterSize.W154)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(LocalCineJetSpacing.current.small),
                modifier = Modifier.weight(1f)
            ) {
                CineJetText(
                    text = review.author,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                CineJetText(
                    text = review.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.extraMedium))

        Divider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(LocalCineJetSpacing.current.extraMedium))

    }
}
