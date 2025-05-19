package com.msk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class JvmLibraryConventionPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")

            }

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = com.msk.convention.AppConfig.JAVA_VERSION
                targetCompatibility = com.msk.convention.AppConfig.JAVA_VERSION

                tasks.withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        jvmTarget = com.msk.convention.AppConfig.JAVA_VERSION.toString()
                    }
                }
            }
        }
    }
}