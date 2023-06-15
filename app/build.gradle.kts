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

    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.travel"
        minSdk = 22
        targetSdk = 32
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
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    flavorDimensions += "version"

    productFlavors {
        create("travel") {
            dimension = "version"
            versionCode = defaultConfig.versionCode
            versionName = defaultConfig.versionName
            versionNameSuffix = "_${defaultConfig.versionName}"
        }
    }
    applicationVariants.all {
        this.outputs.all {
            val appName = "TravelApp"
            val buildType = buildTypes.getByName("release")
            "$appName _$buildType _${android.defaultConfig.versionName}"
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
    // 高德地图
    implementation("com.amap.api:3dmap:latest.integration")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation("androidx.core:core-splashscreen:1.0.1")
    // Room
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    // Glide
    implementation("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
    // Paging 3.1
    implementation("androidx.paging:paging-runtime:3.1.1")
    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    // permissionx
    implementation("com.guolindev.permissionx:permissionx:1.7.1")
    // 基础依赖包，必须要依赖
    implementation("com.geyifeng.immersionbar:immersionbar:3.2.2")
    // kotlin扩展（可选）
    implementation("com.geyifeng.immersionbar:immersionbar-ktx:3.2.2")
    // 轮子哥开源Toaster
    implementation("com.github.getActivity:Toaster:12.2")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}