plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("../keystore/travel.jks")
            storePassword = "lijintao"
            keyAlias = "travel"
            keyPassword = "lijintao"
        }
    }

    compileSdk = SDKVersion.compileSdk

    defaultConfig {
        applicationId = SDKVersion.applicationId
        minSdk = SDKVersion.minSdk
        targetSdk = SDKVersion.targetSdk
        versionCode = 100
        versionName = "1.0.0"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
        ndk {
            abiFilters.add("armeabi")
            abiFilters.add("armeabi-v7a")
            abiFilters.add("arm64-v8a")
            abiFilters.add("x86")
            abiFilters.add("x86_64")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    applicationVariants.all {
        val buildType = this.buildType.name
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                val appName = "TravelApp"
                outputFileName = appName.plus("_")
                    .plus(buildType)
                    .plus(android.defaultConfig.versionName)
                    .plus(".apk")
            }
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
        dataBinding = true
    }
}

dependencies {
    implementation(project(":network"))
    // 高德地图
    implementation(DependenciesLibrary.amap)
    implementation(DependenciesLibrary.hilt_navigation_fragment)
    implementation(DependenciesLibrary.lifecycle_runtime_ktx)
    // Hilt
    implementation(DependenciesLibrary.hilt_android)
    kapt(DependenciesLibrary.hilt_compiler)
    implementation(DependenciesLibrary.splashscreen)
    // Room
    implementation(DependenciesLibrary.room_runtime)
    implementation(DependenciesLibrary.room_ktx)
    kapt(DependenciesLibrary.room_compiler)
    // Glide
    implementation(DependenciesLibrary.glide)
    annotationProcessor(DependenciesLibrary.glide_compiler)
    // Paging 3.1
    implementation(DependenciesLibrary.paging3)
    // retrofit
    implementation(DependenciesLibrary.retrofit)
    implementation(DependenciesLibrary.retrofit_converter_gson)
    // OkHttp
    implementation(DependenciesLibrary.okhttp)
    implementation(DependenciesLibrary.okhttp_log_interceptor)
    // navigation
    implementation(DependenciesLibrary.navigation_fragment_ktx)
    implementation(DependenciesLibrary.navigation_ui_ktx)
    // permissionx
    implementation(DependenciesLibrary.permissionx)
    // 基础依赖包，必须要依赖
    implementation(DependenciesLibrary.immersionbar)
    // kotlin扩展（可选）
    implementation(DependenciesLibrary.immersionbar_ktx)
    // 轮子哥开源Toaster
    implementation(DependenciesLibrary.toaster)

    implementation(DependenciesLibrary.datastore)
    implementation(DependenciesLibrary.datastore_core)
    implementation(DependenciesLibrary.androidx_core_ktx)
    implementation(DependenciesLibrary.appcompat)
    implementation(DependenciesLibrary.material)
    implementation(DependenciesLibrary.constraintlayout)
    testImplementation(DependenciesLibrary.junit)
    androidTestImplementation(DependenciesLibrary.android_test)
    androidTestImplementation(DependenciesLibrary.androidx_test_espresso)
}