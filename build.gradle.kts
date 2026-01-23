import dev.freya02.gradle.tasks.DeployWikiTask
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

    // SQL
    runtimeOnly(libs.postgresql)
    implementation(libs.flyway.core)
    runtimeOnly(libs.flyway.postgresql)
    implementation(libs.hikaricp)
}

kotlin.compilerOptions {
    freeCompilerArgs.addAll(
        "-opt-in=dev.freya02.botcommands.typesafe.messages.api.annotations.ExperimentalTypesafeMessagesApi",
    )
}

val deployWiki by tasks.registering(DeployWikiTask::class) {
    group = "wiki"
    currentBCVersion = libs.versions.botcommands
}
