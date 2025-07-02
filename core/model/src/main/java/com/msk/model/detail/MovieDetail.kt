package com.msk.model.detail

import java.time.LocalDate


data class MovieDetail(
val adult: Boolean,
val backdropPath: String,
val budget: Int,
val casts: List<Cast>,
val genres: List<Genre>,
val homepage: String?,
val id: Int,
val images: Images,
val imdbId: String?,
val originCountry: List<String>,
val originalLanguage: String,
val originalTitle: String,
val overview: String,
val popularity: Double,
val posterPath: String?,
val recommendations: List<Recommendation>,
val releaseDate: LocalDate,
val revenue: Int,
val runtime: String,
val status: String,
val tagline: String?,
val title: String,
val video: Boolean,
val voteAverage: Double,
val voteCount: Int
)

