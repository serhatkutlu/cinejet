package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.util.Constants.CastFields
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    @SerialName(CastFields.ADULT) val adult: Boolean,
    @SerialName(CastFields.CAST_ID) val castId: Int,
    @SerialName(CastFields.CHARACTER) val character: String,
    @SerialName(CastFields.CREDIT_ID) val creditId: String,
    @SerialName(CastFields.GENDER) val gender: Int,
    @SerialName(CastFields.ID) val id: Int,
    @SerialName(CastFields.KNOWN_FOR_DEPARTMENT) val knownForDepartment: String,
    @SerialName(CastFields.NAME) val name: String,
    @SerialName(CastFields.ORDER) val order: Int,
    @SerialName(CastFields.ORIGINAL_NAME) val originalName: String,
    @SerialName(CastFields.POPULARITY) val popularity: Double,
    @SerialName(CastFields.PROFILE_PATH) val profilePath: String?
)