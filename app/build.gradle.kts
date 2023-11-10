plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.weathertestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weathertestapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Location access
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    // navigation
    val nav_version = "2.5.3"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // RxJava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // Dagger
    implementation ("com.google.dagger:dagger:2.47")
    annotationProcessor ("com.google.dagger:dagger-compiler:2.47")

    //Gide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //swiperefresh
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

}