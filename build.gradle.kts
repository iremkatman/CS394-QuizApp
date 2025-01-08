plugins {
    alias(libs.plugins.android.application) apply false       // Android Application Plugin
    alias(libs.plugins.kotlin.android) apply false           // Kotlin Android Plugin
    alias(libs.plugins.androidx.navigation.safe.args) apply false // Safe Args Plugin
    id("com.google.gms.google-services") version "4.4.2" apply false// Firebase Google Services Plugin
}
