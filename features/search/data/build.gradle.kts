plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mskfeature.search.data"
}
dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":features:search:domain"))
    implementation(project(":core:database"))
    implementation(project(":core:network-helper"))
    implementation(libs.androidx.paging3)

}