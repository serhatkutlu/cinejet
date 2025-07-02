package com.msk.model.detail


data class MovieVideo(
    val name: String,
    val videoKey: String,
    val site: String,
    val size: Int,
    val type: String,
    val isOfficial: Boolean,
    val id: String
)