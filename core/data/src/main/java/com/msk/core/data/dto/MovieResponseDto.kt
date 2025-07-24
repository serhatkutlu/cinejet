package com.msk.core.data.dto

import com.msk.core.data.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponseDto(
    @SerialName(Constants.Fields.DATES)
    val dates: DatesDto?,
    @SerialName(Constants.Fields.PAGE)
    val page: Int,
    @SerialName(Constants.Fields.RESULTS)
    val results: List<MovieDto>,
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