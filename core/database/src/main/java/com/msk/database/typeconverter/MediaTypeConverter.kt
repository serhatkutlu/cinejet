package com.msk.database.typeconverter

import androidx.room.TypeConverter
import com.msk.common.util.MediaType

internal class MediaTypeConverter {

    @TypeConverter
    fun fromMediaTypeList(mediaTypes: List<MediaType>): String {
        return mediaTypes.joinToString(",") { it.name }
    }

    @TypeConverter
    fun toMediaTypeList(data: String): List<MediaType> {
        return if (data.isBlank()) emptyList()
        else data.split(",").mapNotNull { name ->
            runCatching { MediaType.valueOf(name) }.getOrNull()
        }
    }
}