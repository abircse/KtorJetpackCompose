plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10" // Match Kotlin version
}

android {
    namespace = "com.ktorjetpackcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ktorjetpackcompose"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("io.ktor:ktor-client-core:2.3.4") // Replace with the latest version
    implementation ("io.ktor:ktor-client-cio:2.3.4") // CIO engine for HTTP requests
    implementation ("io.ktor:ktor-client-serialization:2.3.4") // JSON serialization
    implementation ("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation ("io.ktor:ktor-client-content-negotiation:2.3.4") // Content Nagotiation
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.2") // Lifecycle for Compose
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Coroutines
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // ViewModel
    implementation ("io.ktor:ktor-client-okhttp:2.3.4") // Use OkHttp as the Ktor engine
    implementation ("io.ktor:ktor-client-logging:2.3.4") // Ktor logging plugin

    implementation ("androidx.paging:paging-runtime:3.1.1")  // Paging 3
    implementation ("androidx.paging:paging-compose:1.0.0-alpha14")  // For Jetpack Compose support

}