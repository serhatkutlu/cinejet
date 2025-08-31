package com.msk.cinejet.ui


import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }

    fun Context.findActivity(): ComponentActivity? {
        var ctx = this
        while (ctx is ContextWrapper) {
            if (ctx is ComponentActivity) return ctx
            ctx = ctx.baseContext
        }
        return null
    }
}
