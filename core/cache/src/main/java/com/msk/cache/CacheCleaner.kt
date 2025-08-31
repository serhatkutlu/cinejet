package com.msk.cache

import com.msk.database.manager.CacheDatabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import javax.inject.Inject

class CacheCleaner @Inject constructor(
    private val cacheDatabaseManager: CacheDatabaseManager,
    private val okHttpClient: OkHttpClient,
) {
    suspend fun clearCache() = withContext(Dispatchers.IO) {

        try {
            okHttpClient.cache?.evictAll()
        } catch (_: Exception) {
        }
        try {
            cacheDatabaseManager.clearAllTables()
        } catch (_: Exception) {
        }
    }


}
