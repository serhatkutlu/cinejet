package com.msk

import com.msk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            dependencies {
                "implementation"(libs.findLibrary("androidx-room-runtime").get())
                "ksp"(libs.findLibrary("androidx-room-compiler").get())
                "implementation"(libs.findLibrary("androidx-room-ktx").get())
                "implementation"(libs.findLibrary("androidx-room-paging").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())

            }
        }
    }
}
