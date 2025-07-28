package com.msk.database.model.seeall

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msk.database.util.Constants
import com.msk.model.common.MediaType

@Entity(tableName = Constants.Tables.MOVIE_REMOTE_KEY)
data class MovieRemoteKeyEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.Columns.ID)
    val id: Int,

    @ColumnInfo(name = Constants.Columns.MEDIA_TYPE)
    val mediaType: MediaType,

    @ColumnInfo(name = Constants.Columns.PREV_PAGE)
    val prevPage: Int?,

    @ColumnInfo(name = Constants.Columns.NEXT_PAGE)
    val nextPage: Int?
)