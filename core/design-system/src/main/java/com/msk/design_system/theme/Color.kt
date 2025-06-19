package com.msk.design_system.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
private val primaryLight = Color(0xFF246488)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val primaryContainerLight = Color(0xFFC8E6FF)
private val onPrimaryContainerLight = Color(0xFF001E2E)
private val secondaryLight = Color(0xFF4F616E)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val secondaryContainerLight = Color(0xFFD2E5F5)
private val onSecondaryContainerLight = Color(0xFF0B1D29)
private val tertiaryLight = Color(0xFF63597C)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val tertiaryContainerLight = Color(0xFFE9DDFF)
private val onTertiaryContainerLight = Color(0xFF1F1635)
private val errorLight = Color(0xFFBA1A1A)
private val onErrorLight = Color(0xFFFFFFFF)
private val errorContainerLight = Color(0xFFFFDAD6)
private val onErrorContainerLight = Color(0xFF410002)
private val backgroundLight = Color(0xFFF6FAFE)
private val onBackgroundLight = Color(0xFF181C20)
private val surfaceLight = Color(0xFFF6FAFE)
private val onSurfaceLight = Color(0xFF181C20)
private val surfaceVariantLight = Color(0xFFDDE3EA)
private val onSurfaceVariantLight = Color(0xFF41484D)
private val outlineLight = Color(0xFF71787E)
private val outlineVariantLight = Color(0xFFC1C7CE)
private val scrimLight = Color(0xFF000000)
private val inverseSurfaceLight = Color(0xFF2D3135)
private val inverseOnSurfaceLight = Color(0xFFEEF1F6)
private val inversePrimaryLight = Color(0xFF93CDF6)

// Dark Theme Colors
private val primaryDark = Color(0xFF93CDF6)
private val onPrimaryDark = Color(0xFF00344D)
private val primaryContainerDark = Color(0xFF004C6D)
private val onPrimaryContainerDark = Color(0xFFC8E6FF)
private val secondaryDark = Color(0xFFB7C9D8)
private val onSecondaryDark = Color(0xFF21323E)
private val secondaryContainerDark = Color(0xFF384956)
private val onSecondaryContainerDark = Color(0xFFD2E5F5)
private val tertiaryDark = Color(0xFFCDC0E9)
private val onTertiaryDark = Color(0xFF342B4B)
private val tertiaryContainerDark = Color(0xFF4B4163)
private val onTertiaryContainerDark = Color(0xFFE9DDFF)
private val errorDark = Color(0xFFFFB4AB)
private val onErrorDark = Color(0xFF690005)
private val errorContainerDark = Color(0xFF93000A)
private val onErrorContainerDark = Color(0xFFFFDAD6)
private val backgroundDark = Color(0xFF101417)
private val onBackgroundDark = Color(0xFFDFE3E7)
private val surfaceDark = Color(0xFF101417)
private val onSurfaceDark = Color(0xFFDFE3E7)
private val surfaceVariantDark = Color(0xFF41484D)
private val onSurfaceVariantDark = Color(0xFFC1C7CE)
private val outlineDark = Color(0xFF8B9198)
private val outlineVariantDark = Color(0xFF41484D)
private val scrimDark = Color(0xFF000000)
private val inverseSurfaceDark = Color(0xFFDFE3E7)
private val inverseOnSurfaceDark = Color(0xFF2D3135)
private val inversePrimaryDark = Color(0xFF246488)


internal val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,

)

internal val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
)