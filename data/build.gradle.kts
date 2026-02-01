plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.firebase.bom)
            implementation(libs.firebase.firestore)
            implementation(project(":domain"))
            implementation(libs.kotlinx.serialization)
        }
    }
}