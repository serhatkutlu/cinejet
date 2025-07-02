plugins {
    alias(libs.plugins.cinejet.android.library.compose)
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.android.ui)
    alias(libs.plugins.cinejet.hilt)

}

android {
    namespace = "com.msk.feature.home.ui"
}
dependencies{
    implementation(project(":features:home:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}