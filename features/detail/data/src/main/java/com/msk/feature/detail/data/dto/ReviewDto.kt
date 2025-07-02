package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.ReviewFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    @SerialName(ReviewFields.AUTHOR) val author: String,
    @SerialName(ReviewFields.AUTHOR_DETAILS) val authorDetails: AuthorDetailsDto,
    @SerialName(ReviewFields.CONTENT) val content: String,
    @SerialName(ReviewFields.CREATED_AT) val createdAt: String,
    @SerialName(ReviewFields.ID) val id: String,
    @SerialName(ReviewFields.UPDATED_AT) val updatedAt: String,
    @SerialName(ReviewFields.URL) val url: String
)