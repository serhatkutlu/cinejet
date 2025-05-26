package com.msk.database.util

import androidx.room.withTransaction
import com.msk.database.CinejetDatabase

class RoomTransactionProvider(
    private val database: CinejetDatabase
)  {

    suspend fun <T> withTransaction(block: suspend () -> T): T {
        return database.withTransaction{
            block()
        }
    }
}