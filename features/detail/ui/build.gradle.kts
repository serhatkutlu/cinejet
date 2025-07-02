plugins {
    alias(libs.plugins.cinejet.android.library.compose)
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.android.ui)
    alias(libs.plugins.cinejet.hilt)
}

android {
    namespace = "com.msk.feature.detail.ui"

}

dependencies {
    implementation(project(":features:detail:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.androidx.paging3)
    implementation(libs.androidx.paging3Compose)
    implementation (libs.androidyoutubeplayer.core)



}