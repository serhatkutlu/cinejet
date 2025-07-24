package com.msk.feature.search.data.dto

import com.msk.feature.search.data.util.Constants.MovieFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName(MovieFields.ADULT)
    val adult: Boolean,

    @SerialName(MovieFields.BACKDROP_PATH)
    val backdropPath: String? = null,

    @SerialName(MovieFields.GENRE_IDS)
    val genreIds: List<Int>,

    @SerialName(MovieFields.ID)
    val id: Int,

    @SerialName(MovieFields.ORIGINAL_LANGUAGE)
    val originalLanguage: String,

    @SerialName(MovieFields.ORIGINAL_TITLE)
    val originalTitle: String,

    @SerialName(MovieFields.OVERVIEW)
    val overview: String,

    @SerialName(MovieFields.POPULARITY)
    val popularity: Double,

    @SerialName(MovieFields.POSTER_PATH)
    val posterPath: String? = null,

    @SerialName(MovieFields.RELEASE_DATE)
    val releaseDate: String? = null,

    @SerialName(MovieFields.TITLE)
    val title: String,

    @SerialName(MovieFields.VIDEO)
    val video: Boolean,

    @SerialName(MovieFields.VOTE_AVERAGE)
    val voteAverage: Double,

    @SerialName(MovieFields.VOTE_COUNT)
    val voteCount: Int
)