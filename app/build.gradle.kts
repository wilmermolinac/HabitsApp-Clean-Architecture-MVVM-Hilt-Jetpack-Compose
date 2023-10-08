import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wamcdevs.habitsappmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wamcdevs.habitsappmvvm"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    // Esto se uso para solucionar un error de una libreria de kotlin con compose
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    // Para poder usar fechas en apis 25 o menores y no tener errores
    //Get day of week api 25 or lower
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:2.0.3")

    // Para El time picker del tiempo
    val time_picker = "1.1.0"
    implementation ("com.maxkeppeler.sheets-compose-dialogs:core:$time_picker")
    implementation ("com.maxkeppeler.sheets-compose-dialogs:clock:$time_picker")

    val compose_version = "1.5.3"
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.activity:activity-compose:1.8.0")
    implementation ("androidx.compose.ui:ui:$compose_version")
    implementation ("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation ("androidx.compose.material3:material3:1.1.2")

    val corroutine_version = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$corroutine_version")

    // Compose Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.4")

    // Firebase
    implementation (platform("com.google.firebase:firebase-bom:31.2.2"))
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    // Dagger Hilt
    val hilt_version = "2.48"
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    kapt ("com.google.dagger:hilt-compiler:$hilt_version")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // Coil
    implementation ("io.coil-kt:coil-compose:2.4.0")

    // Pager
    val accompanist_version = "0.30.1"
    implementation ("com.google.accompanist:accompanist-pager:$accompanist_version")
    implementation ("com.google.accompanist:accompanist-pager-indicators:$accompanist_version")

    // Permissions
    implementation ("com.google.accompanist:accompanist-permissions:$accompanist_version")

    // Room
    val room_version = "2.5.2"
    implementation( "androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-runtime:$room_version")

    // Retrofit
    val retrofit_version = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // WorkManager
    val workmanager_version = "2.8.1"
    implementation ("androidx.work:work-runtime-ktx:$workmanager_version")
    implementation ("androidx.hilt:hilt-work:1.0.0")

    // Testing
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:$hilt_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$compose_version")
    testImplementation ("app.cash.turbine:turbine:0.12.0")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:$hilt_version")
    val mockk_version = "1.13.4"
    testImplementation ("io.mockk:mockk:$mockk_version")
    androidTestImplementation ("androidx.work:work-testing:$workmanager_version")

}