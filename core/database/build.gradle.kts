plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.room.library)
    alias(libs.plugins.cinejet.hilt)

}
android{
    namespace = "com.msk.database"
}
dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    // Android instrumented testing for Room and coroutines

    // AndroidX Test core for ApplicationProvider / InstrumentationRegistry
    androidTestImplementation(libs.core.ktx)
}