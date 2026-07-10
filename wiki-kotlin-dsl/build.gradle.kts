plugins {
    `kotlin-conventions`
}

dependencies {
    implementation(project(":wiki-commons"))
    implementation(project(":wiki-kotlin-commons"))
    implementation(libs.botcommands.jda.ktx)
}
