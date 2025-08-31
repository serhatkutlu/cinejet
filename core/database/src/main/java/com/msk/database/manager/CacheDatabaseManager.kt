package com.msk.database.manager

import com.msk.database.CinejetDatabase
import javax.inject.Inject

class CacheDatabaseManager @Inject constructor(
    private val cineJetDatabase: CinejetDatabase
) {
      fun clearAllTables() {
        cineJetDatabase.clearAllTables()

    }
}
