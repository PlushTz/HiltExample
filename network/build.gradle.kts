import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("kotlin-kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.network"
    compileSdk = SDKVersion.compileSdk

    defaultConfig {
        minSdk = SDKVersion.minSdk
        targetSdk = SDKVersion.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Hilt
    implementation(DependenciesLibrary.hilt_android)
    kapt(DependenciesLibrary.hilt_compiler)
    implementation(DependenciesLibrary.retrofit)
    implementation(DependenciesLibrary.retrofit_converter_gson)
    implementation(DependenciesLibrary.okhttp)
    implementation(DependenciesLibrary.okhttp_log_interceptor)
    implementation(DependenciesLibrary.androidx_core_ktx)
    implementation(DependenciesLibrary.appcompat)
    implementation(DependenciesLibrary.material)
    testImplementation(DependenciesLibrary.junit)
    androidTestImplementation(DependenciesLibrary.android_test)
    androidTestImplementation(DependenciesLibrary.androidx_test_espresso)
}