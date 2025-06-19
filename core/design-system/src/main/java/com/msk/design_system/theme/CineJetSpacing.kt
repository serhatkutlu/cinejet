package com.msk.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass


internal val CompactSpacing = CineJetSpacing(
    default = 0.dp,
    extraSmall = 4.dp,
    small = 8.dp,
    smallMedium = 12.dp,
    medium = 16.dp,
    extraMedium = 24.dp,
    large = 32.dp,
    extraLarge = 40.dp,
    largest = 64.dp
)

internal val RelaxedSpacing = CineJetSpacing(
    default = 0.dp,
    extraSmall = 8.dp,
    small = 12.dp,
    smallMedium = 16.dp,
    medium = 24.dp,
    extraMedium = 32.dp,
    large = 48.dp,
    extraLarge = 64.dp,
    largest = 96.dp
)

@Immutable
 data class CineJetSpacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val smallMedium: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val extraMedium: Dp = 24.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 40.dp,
    val largest: Dp = 64.dp
)

@Composable
internal fun ProvideCineJetSpacing(
    windowSizeClass: WindowSizeClass,
    content: @Composable () -> Unit
) {
    val spacing = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT->CompactSpacing
        WindowWidthSizeClass.MEDIUM->RelaxedSpacing
        WindowWidthSizeClass.EXPANDED->RelaxedSpacing
        else -> CompactSpacing

    }
    CompositionLocalProvider(
        LocalCineJetSpacing provides spacing,
        content = content
    )
}

 val LocalCineJetSpacing = staticCompositionLocalOf { CompactSpacing }