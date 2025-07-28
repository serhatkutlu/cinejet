package com.msk.database.model.detail

import kotlinx.serialization.Serializable


@Serializable
data class ImagesEntity(
    val backdrops: List<ImagesItemEntity>,
    val posters: List<ImagesItemEntity>

)

@Serializable
data class ImagesItemEntity(
    val aspectRatio: Float,
    val height: Int,
    val language: String? = null,
    val filePath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)