plugins {
    alias(libs.plugins.cinejet.android.library.compose)
    alias(libs.plugins.cinejet.android.library)

}

android {
    namespace = "com.msk.core.design_system"

}
dependencies{
    implementation(libs.coilCompose)
    api(libs.androidx.material3.adaptive.navigation.suite.android)

    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
