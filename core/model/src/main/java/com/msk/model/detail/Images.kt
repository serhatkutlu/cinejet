package com.msk.model.detail

import org.intellij.lang.annotations.Language

data class Images(
    val backdrops: List<ImageItem>,
    val posters: List<ImageItem>

)

data class ImageItem(
    val aspectRatio: Float,
    val height: Int,
    val language: String? = null,
    val filePath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int
)