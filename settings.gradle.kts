pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        // kotlin-serialization
        maven(url = "https://kotlin.bintray.com/kotlinx")
        // Lottie
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Foodies"
include(":app")
include(":domain")
include(":data")
