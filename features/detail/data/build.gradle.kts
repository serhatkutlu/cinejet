plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.msk.features.detail.data"

}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":features:detail:domain"))
    implementation(libs.androidx.paging3)

}
