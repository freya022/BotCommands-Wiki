package io.github.freya022.wiki.localization

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.freya022.botcommands.api.core.BContext
import io.github.freya022.botcommands.api.localization.LocalizationMapRequest
import io.github.freya022.botcommands.api.localization.readers.AbstractJacksonLocalizationMapReader
import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.InputStream
import java.nio.file.Path
import kotlin.io.path.inputStream
import kotlin.io.path.notExists
import kotlin.io.path.pathString

private val logger = KotlinLogging.logger { }

// --8<-- [start:local_localization_map_reader-kotlin]
class LocalLocalizationMapReader(
    context: BContext,
    mapper: ObjectMapper,
    private val basePath: Path,
) : AbstractJacksonLocalizationMapReader(context, mapper) {

    override fun getInputStream(request: LocalizationMapRequest): InputStream? {
        val fileName = "${request.bundleName}.json"
        val path = basePath.resolve(fileName)
        if (path.notExists()) {
            logger.trace { "Found no bundle at ${path.pathString}" }
            return null
        }

        return path.inputStream()
    }
}
// --8<-- [end:local_localization_map_reader-kotlin]
