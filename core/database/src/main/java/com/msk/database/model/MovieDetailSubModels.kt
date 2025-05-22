package com.msk.database.model

import com.msk.database.util.Constants
import com.msk.database.util.Constants.Columns.ID
import com.msk.database.util.Constants.Columns.LOGO_PATH
import com.msk.database.util.Constants.Columns.NAME
import com.msk.database.util.Constants.Columns.ORIGIN_COUNTRY
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerialName(ID)
    val id: Int,
    @SerialName(NAME)
    val name: String
)

@Serializable
data class ProductionCompany(
    @SerialName(ID)
    val id: Int,

    @SerialName(NAME)
    val name: String,

    @SerialName(LOGO_PATH)
    val logoPath: String?,

    @SerialName(ORIGIN_COUNTRY)
    val originCountry: String
)


@Serializable
data class Cast(

    @SerialName(NAME)
    val name: String,

    @SerialName(Constants.Columns.CHARACTER)
    val character: String,

    @SerialName(Constants.Columns.ORDER)
    val order: Int,

    @SerialName(Constants.Columns.ORIGINAL_NAME)
    val originalName: String,

    @SerialName(Constants.Columns.POPULARITY)
    val popularity: Double,

    @SerialName(Constants.Columns.PROFILE_PATH)
    val profilePath: String?
)
