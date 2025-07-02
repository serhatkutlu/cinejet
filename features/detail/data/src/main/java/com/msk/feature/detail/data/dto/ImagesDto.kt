package com.msk.feature.detail.data.dto


import com.msk.feature.detail.data.util.Constants.ImageItemFields
import com.msk.feature.detail.data.util.Constants.ImagesFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    @SerialName(ImagesFields.BACKDROPS) val backdrops: List<ImageItemDto>,

    @SerialName(ImagesFields.POSTERS) val posters: List<ImageItemDto>
)

@Serializable
data class ImageItemDto(
    @SerialName(ImageItemFields.ASPECT_RATIO) val aspectRatio: Float,
    @SerialName(ImageItemFields.HEIGHT) val height: Int,
    @SerialName(ImageItemFields.ISO_639_1) val iso6391: String? = null,
    @SerialName(ImageItemFields.FILE_PATH) val filePath: String,
    @SerialName(ImageItemFields.VOTE_AVERAGE) val voteAverage: Double,
    @SerialName(ImageItemFields.VOTE_COUNT) val voteCount: Int,
    @SerialName(ImageItemFields.WIDTH) val width: Int
)