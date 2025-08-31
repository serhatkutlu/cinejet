package com.msk.design_system.util

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.msk.common.util.Constants
import com.msk.core.design_system.R
import java.io.File

object CineJetImageLoader {

    fun create(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(File(context.cacheDir, Constants.IMAGE_LOADER_CACHE_DIR))
                    .maxSizeBytes(150L * 1024 * 1024)
                    .build()
            }
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .build()
    }
}
