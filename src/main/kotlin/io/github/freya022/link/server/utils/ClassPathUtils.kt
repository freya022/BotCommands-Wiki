package io.github.freya022.link.server.utils

import io.github.classgraph.ClassGraph
import io.github.freya022.botcommands.api.core.BotCommands
import kotlin.metadata.jvm.KotlinClassMetadata
import kotlin.streams.asSequence

private val metadataAnnotationName = Metadata::class.java.name

val apiClasses: List<KotlinClass> = run {
    val libPackages = BotCommands::class.java.classLoader
        .resources("META-INF/bc.packages")
        .asSequence()
        .flatMap { it.readText().trim().lineSequence() }
        .filter { "api" in it }
        .toList()

    ClassGraph()
        .enableClassInfo()
        .enableAnnotationInfo()
        .acceptPackages(*libPackages.toTypedArray())
        .scan()
        .allClasses
        // Only keep Kotlin classes
        .mapNotNull { classInfo ->
            val metadataAnnotationInfo = classInfo.annotationInfo.directOnly()[metadataAnnotationName] ?: return@mapNotNull null
            val metadata = metadataAnnotationInfo.loadClassAndInstantiate() as Metadata
            KotlinClass(classInfo, KotlinClassMetadata.readStrict(metadata))
        }
}

fun List<KotlinClass>.filterBySimpleName(simpleClassName: String): List<KotlinClass> =
    filter { classInfo -> classInfo.simpleNestedName.replace('$', '.') == simpleClassName }
