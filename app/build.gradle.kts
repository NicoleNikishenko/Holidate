plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs").version("2.5.0")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4") // Or latest version of KSP
    id("kotlin-kapt")
}

android {
    namespace = "com.example.holidate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.holidate"
        minSdk = 24
        targetSdk = 33
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Network
    val okhttpVersion = "4.11.0"
    val koinVersion = "3.4.3"
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("io.insert-koin:koin-core:$koinVersion")
    implementation ("io.insert-koin:koin-android:$koinVersion")

    // Moshi - JSON parse
    val moshiVersion = "1.14.0"
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation ("com.squareup.moshi:moshi-adapters:$moshiVersion")

    // Navigation
    val navVersion = "2.7.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    // Location
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    // Room
    val room_version = "2.5.2"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("com.google.code.gson:gson:2.9.0")

}