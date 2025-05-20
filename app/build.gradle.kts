plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.rishi.newsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rishi.newsapp"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    //noinspection GradleDependency
    implementation(libs.androidx.core.ktx.v1160)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //noinspection GradleDependency
    implementation(libs.androidx.constraintlayout.v221)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.androidx.recyclerview)
    //noinspection GradleDependency
    implementation (libs.glide.v4151)
    implementation (libs.retrofit2)
    implementation (libs.convertor.gson)
    implementation (libs.android.lifecycle)
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v287)
    //noinspection GradleDependency
    implementation (libs.androidx.lifecycle.runtime.ktx.v290)
    implementation (libs.dagger.v255)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    kapt (libs.dagger.compiler)
    implementation (libs.androidx.browser)

    implementation (libs.lottie)
    implementation (libs.lottie.compose)
    implementation (libs.logging.interceptor)
    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    //jetpack compose
    implementation (libs.androidx.activity.compose)
    implementation (platform(libs.androidx.compose.bom.v20250500))
    implementation (libs.ui)
    implementation (libs.ui.graphics)
    implementation (libs.ui.tooling.preview)
    implementation (libs.material3)
    implementation (libs.coil.compose)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)
    debugImplementation (libs.ui.tooling)
    debugImplementation (libs.ui.test.manifest)
    implementation (libs.androidx.material)
    //noinspection UseTomlInstead
    implementation ("com.google.accompanist:accompanist-pager:0.30.1")
    implementation (libs.accompanist.pager.indicators)


}