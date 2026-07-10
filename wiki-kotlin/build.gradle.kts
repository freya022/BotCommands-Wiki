plugins {
    `kotlin-conventions`
}

dependencies {
    implementation(project(":wiki-commons"))
    implementation(project(":wiki-kotlin-commons"))
    implementation(libs.botcommands.jda.ktx)
    implementation(libs.botcommands.typesafe.messages.core)
    runtimeOnly(libs.botcommands.typesafe.messages.bc)
}

kotlin.compilerOptions {
    freeCompilerArgs.addAll(
        "-opt-in=dev.freya02.botcommands.typesafe.messages.api.annotations.ExperimentalTypesafeMessagesApi",
    )
}
