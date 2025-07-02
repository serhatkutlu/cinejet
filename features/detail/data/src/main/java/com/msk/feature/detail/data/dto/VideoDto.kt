package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.VideoFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    @SerialName(VideoFields.ISO_639_1) val iso6391: String,
    @SerialName(VideoFields.ISO_3166_1) val iso31661: String,
    @SerialName(VideoFields.NAME) val name: String,
    @SerialName(VideoFields.KEY) val key: String,
    @SerialName(VideoFields.SITE) val site: String,
    @SerialName(VideoFields.SIZE) val size: Int,
    @SerialName(VideoFields.TYPE) val type: String,
    @SerialName(VideoFields.OFFICIAL) val official: Boolean,
    @SerialName(VideoFields.PUBLISHED_AT) val publishedAt: String,
    @SerialName(VideoFields.ID) val id: String
)