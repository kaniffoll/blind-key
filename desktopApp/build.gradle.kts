import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":sharedUI"))
    implementation(libs.firebase.java.sdk)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.app)
    implementation(libs.kermit)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        buildTypes.release {
            proguard {
                isEnabled.set(false)
            }
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Blind key"
            packageVersion = "1.0.0"

            linux {
                iconFile.set(project.file("appIcons/LinuxIcon.png"))
            }
            windows {
                iconFile.set(project.file("appIcons/WindowsIcon.ico"))
            }
            macOS {
                iconFile.set(project.file("appIcons/MacosIcon.icns"))
                bundleID = "org.blindkey.app.blindkey"
            }

            modules(
                "java.sql",
                "jdk.unsupported",
                "java.naming",
                "java.net.http",
                "jdk.crypto.ec"
            )
        }
    }
}
