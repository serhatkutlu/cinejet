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

    implementation(project(":features:see-all:data"))
    implementation(project(":features:see-all:ui"))

    implementation(project(":features:search:data"))
    implementation(project(":features:search:ui"))

    implementation(project(":features:favorites:data"))
    implementation(project(":features:favorites:ui"))

    implementation(project(":features:settings:ui"))


    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:design-system"))
    implementation(project(":core:preferences"))


}
