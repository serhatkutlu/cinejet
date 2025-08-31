plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.android.library.compose)
    alias(libs.plugins.cinejet.android.ui)
    alias(libs.plugins.cinejet.hilt)

}

android {
    namespace = "com.msk.feature.favorites.ui"

}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":features:favorites:domain"))
}