package com.msk.model.detail

data class Recommendation(
    val id: Int,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String,
    val posterPath: String,
)
