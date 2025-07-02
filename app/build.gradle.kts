plugins {
    alias(libs.plugins.cinejet.android.application)
    alias(libs.plugins.cinejet.android.application.compose)
    alias(libs.plugins.cinejet.android.ui)
    alias(libs.plugins.cinejet.hilt)

}

android {
    namespace = "com.msk.cinejet"


}
dependencies {
    implementation(project(":features:home:ui"))
    implementation(project(":features:home:data"))

    implementation(project(":features:detail:data"))
    implementation(project(":features:detail:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:design-system"))


}
