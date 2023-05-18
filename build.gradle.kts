plugins {
    id(Hilt.plugin).version(Hilt.version).apply(false)
    kotlin("jvm").version(kotlinVersion).apply(false)
    id(library).version("7.4.2").apply(false)
    kotlin(android).version(kotlinVersion).apply(false)
    kotlin(serialization).version(kotlinVersion).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}