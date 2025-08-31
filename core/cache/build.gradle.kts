plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.hilt)
}

android {
    namespace = "com.msk.cache"
   }
dependencies{
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:preferences"))
    implementation(project(":core:common"))

}