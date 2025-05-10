// Top-level build.gradle.kts
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.compose.compiler)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add classpath dependencies if needed (usually not required with version catalog)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}