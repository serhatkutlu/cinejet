package com.msk.feature.home.data.dto

import com.msk.feature.home.data.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponseDto(
    @SerialName(Constants.Fields.DATES)
    val dates: com.msk.feature.home.data.dto.DatesDto?,
    @SerialName(Constants.Fields.PAGE)
    val page: Int,
    @SerialName(Constants.Fields.RESULTS)
    val results: List<com.msk.feature.home.data.dto.MovieDto>,
    @SerialName(Constants.Fields.TOTAL_PAGES)
    val totalPages: Int,
    @SerialName(Constants.Fields.TOTAL_RESULTS)
    val totalResults: Int,
    )
@Serializable
data class DatesDto(
    @SerialName(Constants.Fields.MAXIMUM)
    val maximum: String,
    @SerialName(Constants.Fields.MINIMUM)
    val minimum: String
)