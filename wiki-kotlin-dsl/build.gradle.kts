plugins {
    `kotlin-conventions`
}

// Exclude "opus-java" and "tink" from all dependencies as we dont use audio
configurations.all {
    exclude(module = "opus-java")
    exclude(module = "tink")
}

dependencies {
    implementation(project(":wiki-commons"))
    implementation(project(":wiki-kotlin-commons"))
    implementation(libs.botcommands.jda.ktx)
}
