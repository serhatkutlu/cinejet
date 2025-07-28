package com.msk.database.model.detail

import kotlinx.serialization.Serializable


@Serializable
data class CastEntity (
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?,
    val originalName: String,
)