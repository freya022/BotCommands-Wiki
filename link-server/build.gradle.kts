plugins {
    alias(libs.plugins.kotlin)

    alias(libs.plugins.kotlinx.serialization)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.logging)

    // Required for introspection
    implementation(libs.botcommands)

    // JSON
    implementation(libs.kotlinx.serialization.json)

    // Web server
    api(libs.ktor.server.core)
    api(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.resources)

    // Kotlin metadata
    implementation(libs.kotlin.metadata)

    // JUnit
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
