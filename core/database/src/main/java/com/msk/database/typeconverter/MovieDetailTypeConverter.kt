package com.msk.database.typeconverter

import androidx.room.TypeConverter
import com.msk.database.model.detail.CastEntity
import com.msk.database.model.detail.GenreEntity
import com.msk.database.model.detail.ImagesEntity
import com.msk.database.model.detail.ImagesItemEntity
import com.msk.database.model.detail.RecommendationEntity
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class MovieDetailTypeConverter {
    val json = JsonProvider.json


    @TypeConverter
    fun fromGenres(value: List<GenreEntity>): String =
        json.encodeToString(ListSerializer(GenreEntity.serializer()), value)

    @TypeConverter
    fun toGenres(value: String): List<GenreEntity> =
        json.decodeFromString(ListSerializer(GenreEntity.serializer()), value)

//    @TypeConverter
//    fun fromCompanies(value: List<ProductionCompany>): String =
//        json.encodeToString(ListSerializer(ProductionCompany.serializer()), value)
//
//    @TypeConverter
//    fun toCompanies(value: String): List<ProductionCompany> =
//        json.decodeFromString(ListSerializer(ProductionCompany.serializer()), value)

    @TypeConverter
    fun toCast(value: String): List<CastEntity> =
        json.decodeFromString(ListSerializer(CastEntity.serializer()), value)

    @TypeConverter
    fun fromCast(value: List<CastEntity>): String =
        json.encodeToString(ListSerializer(CastEntity.serializer()), value)


}


object ImagesTypeConverter {

    val json = JsonProvider.json


    @TypeConverter
    fun fromImagesEntity(value: ImagesEntity): String =
        json.encodeToString(ImagesEntity.serializer(), value)

    @TypeConverter
    fun toImagesEntity(value: String): ImagesEntity =
        json.decodeFromString(ImagesEntity.serializer(), value)

    @TypeConverter
    fun fromImagesItemEntityList(value: List<ImagesItemEntity>): String =
        json.encodeToString(ListSerializer(ImagesItemEntity.serializer()), value)

    @TypeConverter
    fun toImagesItemEntityList(value: String): List<ImagesItemEntity> =
        json.decodeFromString(ListSerializer(ImagesItemEntity.serializer()), value)
}

object RecommendationTypeConverter{
    val json = JsonProvider.json
    @TypeConverter
    fun fromRecommendation(value: List<RecommendationEntity>): String =
        json.encodeToString(ListSerializer(RecommendationEntity.serializer()), value)

    @TypeConverter
    fun toRecommendation(value: String): List<RecommendationEntity> =
        json.decodeFromString(ListSerializer(RecommendationEntity.serializer()), value)
}

object JsonProvider {
    val json = Json { ignoreUnknownKeys = true }
}