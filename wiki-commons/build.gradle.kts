plugins {
    `kotlin-conventions`

    alias(libs.plugins.kotlinx.serialization)
}

// Exclude "opus-java" and "tink" from all dependencies as we dont use audio
configurations.all {
    exclude(module = "opus-java")
    exclude(module = "tink")
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Discord stuff
    api(libs.jda)
    api(libs.botcommands)

    // Logging
    implementation(libs.logback.classic)
    api(libs.kotlin.logging)

    // JSON
    implementation(libs.kotlinx.serialization.json)

    // SQL
    runtimeOnly(libs.postgresql)
    implementation(libs.flyway.core)
    runtimeOnly(libs.flyway.postgresql)
    implementation(libs.hikaricp)
}
