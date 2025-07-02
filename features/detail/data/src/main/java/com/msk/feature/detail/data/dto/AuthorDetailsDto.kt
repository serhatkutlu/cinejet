package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.AuthorDetailsFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetailsDto(
    @SerialName(AuthorDetailsFields.AVATAR_PATH) val avatarPath: String?,
    @SerialName(AuthorDetailsFields.NAME) val name: String,
    @SerialName(AuthorDetailsFields.RATING) val rating: Double?,
    @SerialName(AuthorDetailsFields.USERNAME) val username: String
)