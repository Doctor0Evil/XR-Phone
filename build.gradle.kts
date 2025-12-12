// File: build.gradle.kts
plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.infra.xrphone"
    compileSdk = 35

    defaultConfig {
        minSdk = 30
        targetSdk = 35

        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"
            }
        }

        ndk {
            abiFilters += listOf("arm64-v8a")
        }

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.7.0"
    }
}

dependencies {
    // Core Kotlin and Android
    implementation("androidx.core:core-ktx:1.15.0")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Jetpack XR / Compose XR integration
    implementation("androidx.xr:xr-core:1.0.0-alpha01")
    implementation("androidx.xr:xr-compose:1.0.0-alpha01")

    // Networking stack
    implementation("org.java-websocket:Java-WebSocket:1.5.6")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Serialization and data handling
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
}
