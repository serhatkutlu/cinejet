package com.msk.feature.home.data.dto

import com.msk.feature.home.data.util.Constants.Fields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDto(
    @SerialName(Fields.BACKDROP_PATH) val backdropPath: String? = null,
    @SerialName(Fields.ID) val id: Int,
    @SerialName(Fields.ORIGINAL_TITLE) val originalTitle: String,
    @SerialName(Fields.OVERVIEW) val overview: String,
    @SerialName(Fields.POSTER_PATH) val posterPath: String? = null,
    @SerialName(Fields.RELEASE_DATE) val releaseDate: String,
    @SerialName(Fields.TITLE) val title: String,
    @SerialName(Fields.VOTE_AVERAGE) val voteAverage: Double,
)