plugins {
    id("com.android.library")
    kotlin("android")
    kotlin(serialization)
}

android {
    namespace = "com.vopros.foodies.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    with(Deps) {
        implementation(kotlinSerialization)
    }
    with(Mockito) {
        testImplementation(core)
        testImplementation(kotlin)
    }
    testImplementation("junit:junit:4.13.2")
}