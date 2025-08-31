package com.msk.design_system.util

import androidx.annotation.StringRes
import com.msk.core.design_system.R


enum class ThemePreference(@StringRes val value: Int) {
    SYSTEM(R.string.system), LIGHT(R.string.light), DARK(R.string.dark), Dynamic(R.string.dynamic)
}