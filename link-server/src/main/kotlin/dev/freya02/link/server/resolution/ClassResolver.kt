package dev.freya02.link.server.resolution

import dev.freya02.link.server.LinkException
import dev.freya02.link.server.LinkRepresentation
import dev.freya02.link.server.LinkRequest
import dev.freya02.link.server.utils.apiClasses
import dev.freya02.link.server.utils.filterBySimpleName
import dev.freya02.link.server.utils.getBaseLink
import kotlin.metadata.ClassKind
import kotlin.metadata.Visibility
import kotlin.metadata.jvm.KotlinClassMetadata
import kotlin.metadata.kind
import kotlin.metadata.visibility

object ClassResolver {

    fun singleClass(request: LinkRequest): LinkRepresentation {
        val className = request.identifier
        val classes = apiClasses.filterBySimpleName(className)
        if (classes.isEmpty()) {
            throw LinkException("'$className' was not found")
        } else if (classes.size > 1) {
            throw LinkException("Found multiple candidates for '$className': ${classes.joinToString { it.fullName }}")
        } else {
            val kotlinClass = classes.first()
            val kmClass = (kotlinClass.metadata as? KotlinClassMetadata.Class)?.kmClass
                ?: throw LinkException("'$className' is not a class")

            if (kmClass.visibility != Visibility.PUBLIC) {
                throw LinkException("'$className' is not public")
            }

            val baseLink = kmClass.getBaseLink(kotlinClass)
            return when (kmClass.kind) {
                ClassKind.ANNOTATION_CLASS -> LinkRepresentation("#!java @$className", "$baseLink/index.html")
                else -> LinkRepresentation(className, "$baseLink/index.html")
            }
        }
    }
}
