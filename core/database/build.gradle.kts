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
}