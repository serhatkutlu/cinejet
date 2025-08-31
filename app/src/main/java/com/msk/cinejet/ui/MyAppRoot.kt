package com.msk.cinejet.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext

val LocalAppRestart = compositionLocalOf { {} }

@Composable
fun MyAppRoot(content: @Composable () -> Unit) {
    val context = LocalContext.current

    CompositionLocalProvider(
        LocalAppRestart provides {
            val activity = context as? Activity
            activity?.recreate()
        }
    ) {
        content()
    }
}