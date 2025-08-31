package com.msk

import com.msk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidUiConventionPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
               "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
               "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(project(":core:design-system"))


            }
        }
    }
}