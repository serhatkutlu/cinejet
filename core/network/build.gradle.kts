import java.util.Properties

plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.retrofit)
    alias(libs.plugins.cinejet.hilt)
}

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.reader().use { load(it) }
    }
}
val apiKey = localProperties.getProperty("TMDB_API_KEY") ?: System.getenv("TMDB_API_KEY")

android{
    namespace="com.msk.network"
    buildFeatures {
        buildConfig = true
    }


    defaultConfig{
        buildConfigField("String", "TMDB_API_KEY", "\"$apiKey\"")

    }
}
dependencies{
    implementation(project(":core:common"))
    implementation(libs.javax.inject)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockwebserver)
}



