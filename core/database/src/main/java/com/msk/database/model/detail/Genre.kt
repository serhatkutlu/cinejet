package com.msk.database.model.detail

import androidx.room.ColumnInfo
import com.msk.database.util.Constants
import kotlinx.serialization.Serializable

@Serializable
data class GenreEntity(
    @ColumnInfo(Constants.Columns.ID) val id: Int,
    @ColumnInfo(Constants.Columns.NAME) val name: String
)
