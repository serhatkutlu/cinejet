package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.MovieReviewsFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReviewsDto(
    @SerialName(MovieReviewsFields.ID) val id: Int,
    @SerialName(MovieReviewsFields.PAGE) val page: Int,
    @SerialName(MovieReviewsFields.RESULTS) val results: List<ReviewDto>,
    @SerialName(MovieReviewsFields.TOTAL_PAGES) val totalPages: Int,
    @SerialName(MovieReviewsFields.TOTAL_RESULTS) val totalResults: Int
)