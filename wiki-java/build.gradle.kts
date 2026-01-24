plugins {
    `java-conventions`
}

// Exclude "opus-java" and "tink" from all dependencies as we dont use audio
configurations.all {
    exclude(module = "opus-java")
    exclude(module = "tink")
}

dependencies {
    implementation(project(":wiki-commons"))
    implementation(libs.botcommands.spring)
    implementation(libs.botcommands.typesafe.messages.core)
    runtimeOnly(libs.botcommands.typesafe.messages.bc)
}
