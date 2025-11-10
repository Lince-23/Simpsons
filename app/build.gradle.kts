plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.0.21"
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.simpsons"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.simpsons"
        minSdk = 26
        targetSdk = 36
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Fragments
    implementation(libs.androidx.fragment.ktx)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //Coroutines
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    //Navigation Component
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    //Paging
    implementation(libs.androidx.paging.runtime)

    //Mockk
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    //Android arch
    testImplementation(libs.androidx.core.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}