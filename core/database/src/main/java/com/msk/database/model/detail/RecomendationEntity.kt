package com.msk.database.model.detail

import kotlinx.serialization.Serializable


@Serializable
data class RecommendationEntity(
    val id: Int,
    val voteCount: Int,
    val voteAverage: Float,
    val title: String,
    val posterPath: String?,
)
