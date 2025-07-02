package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName(Constants.JsonKeys.ID) val id: Int,
    @SerialName(Constants.JsonKeys.NAME) val name: String
)
