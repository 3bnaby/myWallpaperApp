plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.mywallpaperapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mywallpaperapp"
        minSdk = 29
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
    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.paging:paging-common-ktx:3.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //sdp ssp
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")

    //navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")


    //viewModel
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-ktx:1.6.1")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    //paging 3
    implementation("androidx.paging:paging-runtime:3.2.0-alpha03")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    implementation ("io.trbl:blurhash:1.0.0")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")


    //custom progress bar
    implementation ("com.github.ybq:Android-SpinKit:1.4.0")
    implementation ("com.flaviofaria:kenburnsview:1.0.7")
}