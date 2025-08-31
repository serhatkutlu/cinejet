plugins {
    alias(libs.plugins.cinejet.android.library.compose)
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.android.ui)
    alias(libs.plugins.cinejet.hilt)
}

android {
    namespace="com.msk.feature.settings.ui"

}
dependencies{
    implementation(project(":core:model"))
    implementation(project(":core:preferences"))
    implementation(project(":core:cache"))
}

