package com.msk.model.common


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val mediaType: MediaType?,
    val lastFetchedTime: Long?
)
