package com.msk

import com.msk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "api"(libs.findLibrary("retrofit").get())
                "api"(libs.findLibrary("kotlinx.serialization.json").get())
                "api"(libs.findLibrary("okhttp.logging").get())
                "implementation"(libs.findLibrary("retrofit2.kotlinx.serialization.converter").get())
                "implementation"(libs.findLibrary("okhttp").get())

            }
        }
    }
}
