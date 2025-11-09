import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

// Load local.properties
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "com.runanywhere.startup_hackathon20"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.runanywhere.startup_hackathon20"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"


        // API Keys - Read from local.properties
        buildConfigField(
            "String",
            "GOOGLE_MAPS_API_KEY",
            "\"${localProperties.getProperty("GOOGLE_MAPS_API_KEY", "")}\""
        )
        buildConfigField(
            "String",
            "GOOGLE_PLACES_API_KEY",
            "\"${localProperties.getProperty("GOOGLE_PLACES_API_KEY", "")}\""
        )
        buildConfigField(
            "String",
            "UNSPLASH_ACCESS_KEY",
            "\"${localProperties.getProperty("UNSPLASH_ACCESS_KEY", "")}\""
        )
        buildConfigField(
            "String",
            "OPENAI_API_KEY",
            "\"${localProperties.getProperty("OPENAI_API_KEY", "")}\""
        )

        manifestPlaceholders["MAPS_API_KEY"] =
            localProperties.getProperty("GOOGLE_MAPS_API_KEY", "")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Firebase BoM (Bill of Materials)
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // ... your other firebase libraries like firebase-auth-ktx
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth-ktx")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Cloud Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Firebase Storage (optional - for profile pictures)
    implementation("com.google.firebase:firebase-storage-ktx")

    // Coroutines support for Firebase
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // RunAnywhere SDK - Local AARs from GitHub Release v0.1.3-alpha
    // Core SDK (4.01MB)
    implementation(files("libs/RunAnywhereKotlinSDK-release.aar"))
    // LLM Module (2.12MB) - includes llama.cpp with 7 ARM64 CPU variants
    implementation(files("libs/runanywhere-llm-llamacpp-release.aar"))
    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    // Required SDK dependencies (transitive dependencies from AARs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

    // Ktor for networking (required by SDK)
    implementation("io.ktor:ktor-client-core:3.0.3")
    implementation("io.ktor:ktor-client-okhttp:3.0.3")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.3")
    implementation("io.ktor:ktor-client-logging:3.0.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.3")

    // OkHttp (required by SDK)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Retrofit (required by SDK)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Gson (required by SDK)
    implementation("com.google.code.gson:gson:2.11.0")

    // Okio (required by SDK)
    implementation("com.squareup.okio:okio:3.9.1")

    // AndroidX WorkManager (required by SDK)
    implementation("androidx.work:work-runtime-ktx:2.10.0")

    // AndroidX Room (required by SDK)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // AndroidX Security (required by SDK)
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // Standard app dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Google Maps Compose
    implementation("com.google.maps.android:maps-compose:4.4.1")
    implementation("com.google.android.gms:play-services-maps:19.0.0")

// Markdown renderer for AI plans
    implementation("io.noties.markwon:core:4.6.2")

// Coil for loading destination images
    implementation("io.coil-kt:coil-compose:2.6.0")

// Optional (if you add real APIs later)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("androidx.compose.material:material-icons-extended:1.7.0-alpha07")


}