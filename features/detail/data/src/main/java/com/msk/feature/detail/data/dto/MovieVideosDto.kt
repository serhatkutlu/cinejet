package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.MovieVideosFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieVideosDto(
    @SerialName(MovieVideosFields.ID) val id: Int,
    @SerialName(MovieVideosFields.RESULTS) val results: List<VideoDto>
)