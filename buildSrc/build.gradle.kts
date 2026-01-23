import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.plugin)

    // DeployWikiTask
    implementation(libs.ktoml)
    implementation(libs.jackson.dataformat.xml)
    implementation("dev.freya02:link-server")
}

tasks.withType<JavaCompile> {
    options.release = 21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}
