plugins {
    alias(libs.plugins.cinejet.jvm.library)
}
dependencies{
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging3Common)

}
