package com.msk.design_system.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.msk.design_system.util.ThemePreference


@Composable
fun CinejetTheme(
    themePreference :ThemePreference= ThemePreference.SYSTEM,
    content: @Composable () -> Unit
) {
    val darkTheme: Boolean = isSystemInDarkTheme()

    val colorScheme = when (themePreference) {
        ThemePreference.SYSTEM -> if (darkTheme) darkScheme else lightScheme
        ThemePreference.LIGHT -> lightScheme
        ThemePreference.DARK -> darkScheme
        ThemePreference.Dynamic-> if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            if (darkTheme)dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(LocalContext.current)
        } else  if (darkTheme) darkScheme else lightScheme
    }
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> darkScheme
//        else -> MaterialTheme.colorScheme
//    }

    ProvideCineJetSpacing(currentWindowAdaptiveInfo().windowSizeClass) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CineJetTypography,
            content = content
        )
    }



}