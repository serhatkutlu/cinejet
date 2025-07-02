package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.JsonKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName(JsonKeys.ADULT) val adult: Boolean,
    @SerialName(JsonKeys.BACKDROP_PATH) val backdropPath: String,
    @SerialName(JsonKeys.BUDGET) val budget: Int,
    @SerialName(JsonKeys.CREDITS) val credits: CreditsDto,
    @SerialName(JsonKeys.GENRES) val genres: List<GenreDto>,
    @SerialName(JsonKeys.HOMEPAGE) val homepage: String?,
    @SerialName(JsonKeys.ID) val id: Int,
    @SerialName(JsonKeys.IMAGES) val images: ImagesDto,
    @SerialName(JsonKeys.IMDB_ID) val imdbId: String?,
    @SerialName(JsonKeys.ORIGIN_COUNTRY) val originCountry: List<String>,
    @SerialName(JsonKeys.ORIGINAL_LANGUAGE) val originalLanguage: String,
    @SerialName(JsonKeys.ORIGINAL_TITLE) val originalTitle: String,
    @SerialName(JsonKeys.OVERVIEW) val overview: String,
    @SerialName(JsonKeys.POPULARITY) val popularity: Double,
    @SerialName(JsonKeys.POSTER_PATH) val posterPath: String?,
    @SerialName(JsonKeys.RECOMMENDATIONS) val recommendations: RecommendationsDto,
    @SerialName(JsonKeys.RELEASE_DATE) val releaseDate: String,
    @SerialName(JsonKeys.REVENUE) val revenue: Int,
    @SerialName(JsonKeys.RUNTIME) val runtime: Int,
    @SerialName(JsonKeys.STATUS) val status: String,
    @SerialName(JsonKeys.TAGLINE) val tagline: String?,
    @SerialName(JsonKeys.TITLE) val title: String,
    @SerialName(JsonKeys.VIDEO) val video: Boolean,
    @SerialName(JsonKeys.VOTE_AVERAGE) val voteAverage: Double,
    @SerialName(JsonKeys.VOTE_COUNT) val voteCount: Int
)