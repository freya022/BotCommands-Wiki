plugins {
    `kotlin-conventions`
}

dependencies {
    implementation(project(":wiki-commons"))
    implementation(libs.botcommands.spring)
}
