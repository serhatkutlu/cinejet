plugins {
    alias(libs.plugins.cinejet.android.library)
    alias(libs.plugins.cinejet.room.library)

}
dependencies {
    implementation(project(":core:common"))
}