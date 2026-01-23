import nl.littlerobots.vcu.plugin.resolver.VersionSelectors

plugins {
    `kotlin-conventions`

    alias(libs.plugins.kotlinx.serialization)

    alias(libs.plugins.version.catalog.update)
}

versionCatalogUpdate {
    versionSelector(VersionSelectors.PREFER_STABLE)
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
    implementation(libs.jda)
    implementation(libs.botcommands)
    implementation(libs.botcommands.jda.ktx)
    implementation(libs.botcommands.spring)
    implementation(libs.botcommands.typesafe.messages.core)
    runtimeOnly(libs.botcommands.typesafe.messages.bc)

    // Logging
    implementation(libs.logback.classic)
    implementation(libs.kotlin.logging)

    // JSON
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.gson)

    // SQL
    runtimeOnly(libs.postgresql)
    implementation(libs.flyway.core)
    runtimeOnly(libs.flyway.postgresql)
    implementation(libs.hikaricp)

    // Web server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
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

kotlin.compilerOptions {
    freeCompilerArgs.addAll(
        "-opt-in=dev.freya02.botcommands.typesafe.messages.api.annotations.ExperimentalTypesafeMessagesApi",
    )
}
