plugins {
    alias(libs.plugins.cinejet.android.library.compose)
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.android.ui)
    alias(libs.plugins.cinejet.hilt)

}

android {
    namespace = "com.msk.feature.seeall.ui"

}
dependencies {
    implementation(project(":features:see-all:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.androidx.paging3Compose)

}