plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.msk.feature.seeall.data"
}
dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":features:see-all:domain"))
    implementation(project(":core:database"))
    implementation(libs.androidx.paging3Common)

}