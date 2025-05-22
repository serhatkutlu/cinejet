package com.msk.database.typeconverter

import androidx.room.TypeConverter
import com.msk.common.util.MediaType

internal class MediaTypeConverter {
    @TypeConverter
    fun fromMediaType(mediaType: MediaType): String = mediaType.name

    @TypeConverter
    fun toMediaType(value: String): MediaType = MediaType.valueOf(value)
}