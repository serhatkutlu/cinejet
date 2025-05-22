package com.msk.database.dao.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msk.common.util.MediaType
import com.msk.database.model.MovieRemoteKeyEntity
import com.msk.database.util.Constants.Tables.MOVIE_REMOTE_KEY

@Dao
interface MovieRemoteKeyDao {
    @Query("SELECT * FROM $MOVIE_REMOTE_KEY WHERE id = :id AND mediaType = :mediaType")
    suspend fun getByIdAndMediaType(id: Int, mediaType: MediaType): MovieRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<MovieRemoteKeyEntity>)

    @Query("DELETE FROM $MOVIE_REMOTE_KEY WHERE mediaType = :mediaType")
    suspend fun deleteByMediaType(mediaType: MediaType)
}
