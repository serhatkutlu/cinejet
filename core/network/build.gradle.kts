plugins {
    alias(libs.plugins.cinejet.jvm.library)
    alias(libs.plugins.cinejet.retrofit)
}
dependencies{
    implementation(project(":core:common"))
    implementation(libs.javax.inject)

}



