package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.RecommendationsFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecommendationsDto(
    @SerialName(RecommendationsFields.PAGE) val page: Int,
    @SerialName(RecommendationsFields.RESULTS) val results: List<ResultDto>,
    @SerialName(RecommendationsFields.TOTAL_PAGES) val totalPages: Int,
    @SerialName(RecommendationsFields.TOTAL_RESULTS) val totalResults: Int
)