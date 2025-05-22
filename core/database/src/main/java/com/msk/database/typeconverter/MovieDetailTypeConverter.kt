package com.msk.database.typeconverter

import androidx.room.TypeConverter
import com.msk.database.model.Genre
import com.msk.database.model.ProductionCompany

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class MovieDetailTypeConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromGenres(value: List<Genre>): String =
        json.encodeToString(ListSerializer(Genre.serializer()), value)

    @TypeConverter
    fun toGenres(value: String): List<Genre> =
        json.decodeFromString(ListSerializer(Genre.serializer()), value)

    @TypeConverter
    fun fromCompanies(value: List<ProductionCompany>): String =
        json.encodeToString(ListSerializer(ProductionCompany.serializer()), value)

    @TypeConverter
    fun toCompanies(value: String): List<ProductionCompany> =
        json.decodeFromString(ListSerializer(ProductionCompany.serializer()), value)


}
