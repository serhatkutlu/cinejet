package com.msk.feature.search.data.dto

import com.msk.feature.search.data.util.Constants.MovieSearchFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponseDto(
    @SerialName(MovieSearchFields.PAGE)
    val page: Int,

    @SerialName(MovieSearchFields.RESULTS)
    val results: List<MovieDto>,

    @SerialName(MovieSearchFields.TOTAL_PAGES)
    val totalPages: Int,

    @SerialName(MovieSearchFields.TOTAL_RESULTS)
    val totalResults: Int
)