plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.firebase.firestore)
            implementation(libs.koin.core)
            implementation(project(":domain"))
            implementation(project(":data"))
            implementation(libs.androidx.room.runtime)
            implementation(libs.datastore.preferences)
        }
    }
}