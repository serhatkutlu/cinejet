import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
dependencies {
    compileOnly(libs.android.plugin)
    compileOnly(libs.compose.plugin)
    compileOnly(libs.kotlin.plugin)
    compileOnly(libs.ksp.plugin)
    compileOnly(libs.detekt.plugin)
}
gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.cinejet.android.application.compose.get().pluginId
            implementationClass = "com.msk.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.cinejet.android.application.asProvider().get().pluginId
            implementationClass = "com.msk.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.cinejet.android.library.compose.get().pluginId
            implementationClass = "com.msk.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.cinejet.android.library.asProvider().get().pluginId
            implementationClass = "com.msk.AndroidLibraryConventionPlugin"
        }
        register("androidUi") {
            id = libs.plugins.cinejet.android.ui.get().pluginId
            implementationClass = "com.msk.AndroidUiConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.cinejet.hilt.get().pluginId
            implementationClass = "com.msk.HiltConventionPlugin"
        }
        register("room") {
            id = libs.plugins.cinejet.room.library.get().pluginId
            implementationClass = "com.msk.RoomConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.cinejet.jvm.library.get().pluginId
            implementationClass = "com.msk.JvmLibraryConventionPlugin"
        }

        register("retrofit") {
            id = libs.plugins.cinejet.retrofit.get().pluginId
            implementationClass = "com.msk.RetrofitConventionPlugin"
        }
        register("detekt") {
            id = libs.plugins.cinejet.detekt.get().pluginId
            implementationClass = "com.msk.DetektConventionPlugin"
        }

    }
}