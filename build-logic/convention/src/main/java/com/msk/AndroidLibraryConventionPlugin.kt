package com.msk

import com.android.build.gradle.LibraryExtension
import com.msk.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

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
                    testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"

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

                dependencies {
                    "testImplementation"(libs.findLibrary("junit").get())
                    "androidTestImplementation"(libs.findLibrary("androidx-junit").get())
                    "androidTestImplementation"(libs.findLibrary("androidx-espresso-core").get())
                    "androidTestImplementation"(libs.findLibrary("kotlinx-coroutines-core").get())
                    "androidTestImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())
                }

            }

        }
    }
}
