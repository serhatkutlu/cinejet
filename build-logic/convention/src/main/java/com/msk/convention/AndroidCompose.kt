package com.msk.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx-activity-compose").get())
            "androidTestImplementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx-material3").get())
            "implementation"(libs.findLibrary("androidx-ui-tooling-preview").get())
            "debugImplementation"(libs.findLibrary("androidx-ui-tooling").get())
            "testImplementation"(libs.findLibrary("junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx-junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx-espresso-core").get())

        }


    }


}