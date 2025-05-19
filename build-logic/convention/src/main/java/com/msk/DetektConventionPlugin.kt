package com.msk

import com.msk.convention.libs
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class DetektConventionPlugin:Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
            allprojects {

                configure<DetektExtension> {
                    toolVersion = "1.23.6"
                    config.setFrom("${rootProject.projectDir}/app/config/detekt/detekt.yml")
                    source.setFrom("src/main/java", "src/main/kotlin")
                    parallel = false
                    allRules = true
                }

            }

            dependencies {
                "detektPlugins"(libs.findLibrary("detekt").get())
            }
        }
    }
}