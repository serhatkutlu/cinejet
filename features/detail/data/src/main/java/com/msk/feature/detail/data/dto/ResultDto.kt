package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.ResultFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName(ResultFields.ADULT) val adult: Boolean,
    @SerialName(ResultFields.BACKDROP_PATH) val backdropPath: String,
    @SerialName(ResultFields.GENRE_IDS) val genreIds: List<Int>,
    @SerialName(ResultFields.ID) val id: Int,
    @SerialName(ResultFields.MEDIA_TYPE) val mediaType: String,
    @SerialName(ResultFields.ORIGINAL_LANGUAGE) val originalLanguage: String,
    @SerialName(ResultFields.ORIGINAL_TITLE) val originalTitle: String,
    @SerialName(ResultFields.OVERVIEW) val overview: String,
    @SerialName(ResultFields.POPULARITY) val popularity: Double,
    @SerialName(ResultFields.POSTER_PATH) val posterPath: String,
    @SerialName(ResultFields.RELEASE_DATE) val releaseDate: String,
    @SerialName(ResultFields.TITLE) val title: String,
    @SerialName(ResultFields.VIDEO) val video: Boolean,
    @SerialName(ResultFields.VOTE_AVERAGE) val voteAverage: Double,
    @SerialName(ResultFields.VOTE_COUNT) val voteCount: Int
)