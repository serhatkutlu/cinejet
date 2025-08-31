plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.hilt)
}

android {
    namespace = "com.msk.feature.favorite.data"

}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":features:favorites:domain"))
}