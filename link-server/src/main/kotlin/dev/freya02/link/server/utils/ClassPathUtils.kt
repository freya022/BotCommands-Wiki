package dev.freya02.link.server.utils

import io.github.classgraph.ClassGraph
import kotlin.math.min
import kotlin.metadata.jvm.KotlinClassMetadata

private val metadataAnnotationName = Metadata::class.java.name

val apiClasses: List<KotlinClass> = run {
    ClassGraph()
        .enableClassInfo()
        .enableAnnotationInfo()
        .acceptPackages("*.botcommands.*")
        .rejectPackages("*.internal.*")
        .scan()
        .allClasses
        // Only keep Kotlin classes
        .mapNotNull { classInfo ->
            val metadataAnnotationInfo = classInfo.annotationInfo.directOnly()[metadataAnnotationName] ?: return@mapNotNull null
            val metadata = metadataAnnotationInfo.loadClassAndInstantiate() as Metadata
            KotlinClass(classInfo, KotlinClassMetadata.readStrict(metadata))
        }
}

fun List<KotlinClass>.filterByPrefixedSimpleName(query: String): List<KotlinClass> {
    val classNameStartIdx = query.indexOfFirst { it.isUpperCase() }
    // Misnamed class
    if (classNameStartIdx < 0) return emptyList()

    val expectedPackageParts = query.substring(0, (classNameStartIdx - 1).coerceAtLeast(0))
        .split('.')
        .reversed()
    val expectedClassName = query.substring(classNameStartIdx)

    return filter { classInfo ->
        if (classInfo.simpleNestedName.replace('$', '.') != expectedClassName) {
            // Doesn't correspond to the class name
            return@filter false
        }

        // Match each package part by their starting characters
        val packageParts = classInfo.packageName.split('.').reversed()
        for (i in (0..<min(expectedPackageParts.size, packageParts.size)).reversed()) {
            if (!packageParts[i].startsWith(expectedPackageParts[i])) {
                return@filter false
            }
        }

        true
    }
}
