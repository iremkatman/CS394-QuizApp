plugins {
    id("com.android.application")         // Android Application Plugin
    alias(libs.plugins.kotlin.android)                 // Kotlin Android Plugin
    alias(libs.plugins.androidx.navigation.safe.args)  // Safe Args Plugin
    id("kotlin-parcelize")                              // Parcelize Plugin
    id("com.google.gms.google-services")               // Firebase Google Services Plugin
}


android {
    namespace = "com.example.quizapp"
    compileSdk = 35





    defaultConfig {
        applicationId = "com.example.quizapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        dataBinding = true
    }
    viewBinding{
        enable=true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)  // Android KTX
    implementation(libs.androidx.appcompat) // AppCompat
    implementation(libs.material)          // Material Design
    implementation(libs.androidx.activity) // Activity Kütüphanesi
    implementation(libs.androidx.constraintlayout) // ConstraintLayout
    implementation(libs.androidx.navigation.fragment.ktx) // Navigation fragment
    implementation(libs.androidx.navigation.ui.ktx)       // Navigation UI
    implementation(libs.lottie)           // Lottie Animasyonları
    implementation(libs.glide)            // Glide Görüntü Yükleme

    // Lifecycle bağımlılıkları
    implementation(libs.androidx.lifecycle.livedata.ktx)   // LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)   // Lifecycle-aware bileşenler

    // Test ve Android Test bağımlılıkları
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Retrofit ve OkHttp
    implementation(libs.retrofit)                     // Retrofit
    implementation(libs.retrofit.converter.gson)      // Gson Converter
    implementation(libs.okhttp.logging.interceptor)


    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.7.0")) // Firebase BOM
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics")// Authentication
    implementation("com.google.firebase:firebase-firestore-ktx") // Firestore



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


}