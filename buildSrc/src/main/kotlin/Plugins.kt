const val application = "com.android.application"
const val android = "android"
const val library = "com.android.library"
const val kaptPlugin = "kapt"
const val serialization = "plugin.serialization"

const val kotlinVersion = "1.8.20"

object Deps {

    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0"
    const val accompanistNavAnimation = "com.google.accompanist:accompanist-navigation-animation:0.31.2-alpha"
}

object Livecycle {
    private const val version = "2.6.1"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
}

object Hilt {
    const val version = "2.46"
    const val plugin = "com.google.dagger.hilt.android"
    const val compose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val android = "com.google.dagger:hilt-android:$version"
    const val compiler = "com.google.dagger:hilt-compiler:$version"
}

object Lottie {
    private const val version = "6.0.0"
    const val compose = "com.airbnb.android:lottie-compose:$version"
}

object Mockito {
    private const val version = "5.3.1"
    const val core = "org.mockito:mockito-core:$version"
    const val kotlin = "org.mockito.kotlin:mockito-kotlin:4.1.0"
}