package com.msk.model.detail

data class Cast(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?,
    val originalName: String,
    val knownForDepartment: String
)
