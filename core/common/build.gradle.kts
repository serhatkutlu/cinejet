plugins {
    alias(libs.plugins.cinejet.jvm.library)
}
dependencies{
    implementation(libs.kotlinx.coroutines.core)
    implementation(project(":core:model"))
}