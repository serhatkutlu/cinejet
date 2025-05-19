package com.msk

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = com.msk.convention.AppConfig.COMPILE_SDK

                defaultConfig {
                    minSdk = com.msk.convention.AppConfig.MIN_SDK
                    targetSdk = com.msk.convention.AppConfig.TARGET_SDK
                }

                compileOptions {
                    sourceCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                    targetCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                }
            }
        }
    }
}
