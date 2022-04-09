plugins {
    kotlin("js") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.20"
}

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:0.0.1-pre.329-kotlin-1.6.20")))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-css"))
    implementation(kotlinw("react-router-dom"))

    implementation(kotlinw("mui"))
    implementation(kotlinw("mui-icons"))

    implementation(npm("@emotion/react", "11.8.1"))
    implementation(npm("@emotion/styled", "11.8.1"))
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks {
    wrapper {
        gradleVersion = "7.4"
    }
}