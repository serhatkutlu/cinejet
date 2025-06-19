package com.msk

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")


                extensions.configure<ApplicationExtension> {
                    compileSdk = com.msk.convention.AppConfig.COMPILE_SDK

                    defaultConfig {
                        minSdk = com.msk.convention.AppConfig.MIN_SDK
                    }
                    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
                        kotlinOptions {
                            jvmTarget =com.msk.convention.AppConfig.JAVA_VERSION.toString()
                        }
                    }


                    compileOptions {
                        sourceCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                        targetCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                    }
                    defaultConfig.targetSdk = com.msk.convention.AppConfig.TARGET_SDK

                }
            }

        }
    }
}