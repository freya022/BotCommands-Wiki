plugins {
    `java-conventions`
}

dependencies {
    implementation(project(":wiki-commons"))
    implementation(libs.botcommands.spring)
    implementation(libs.botcommands.typesafe.messages.core)
    runtimeOnly(libs.botcommands.typesafe.messages.bc)
}
