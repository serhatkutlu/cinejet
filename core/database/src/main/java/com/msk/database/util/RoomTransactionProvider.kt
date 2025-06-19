package com.msk.database.util

import androidx.room.withTransaction
import com.msk.database.CinejetDatabase
import javax.inject.Inject

class RoomTransactionProvider @Inject constructor(
    private val database: CinejetDatabase
)  {

    suspend fun <T> withTransaction(block: suspend () -> T): T {
        return database.withTransaction{
            block()
        }
    }
}