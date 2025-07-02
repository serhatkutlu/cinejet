package com.msk.feature.detail.data.dto

import com.msk.feature.detail.data.dto.CastDto
import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    val cast: List<CastDto>,
)