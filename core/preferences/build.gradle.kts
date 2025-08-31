plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.hilt)
}

android {
    namespace = "com.msk.preferences"

}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:model"))
    implementation(libs.androidx.datastore)
    androidTestImplementation(libs.androidx.datastore.core)
}