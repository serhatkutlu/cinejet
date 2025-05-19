package com.msk

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("cinejet.detekt")



                extensions.configure<ApplicationExtension> {
                    compileSdk = com.msk.convention.AppConfig.COMPILE_SDK

                    defaultConfig {
                        minSdk = com.msk.convention.AppConfig.MIN_SDK
                        targetSdk= com.msk.convention.AppConfig.TARGET_SDK
                    }


                    compileOptions {
                        sourceCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                        targetCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                    }



                }
            }

        }
    }
}