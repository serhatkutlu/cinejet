package com.msk.feature.detail.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

enum class MovieDetailTab(@StringRes val titleRes: Int) {
    DETAIL(R.string.detail_tab_detail),
    VIDEOS(R.string.detail_tab_videos),
    REVIEWS(R.string.detail_tab_reviews);

    companion object{
        @Composable
        fun fromResString():List<String> {
            return entries.map { stringResource(it.titleRes) }
        }
    }
}
