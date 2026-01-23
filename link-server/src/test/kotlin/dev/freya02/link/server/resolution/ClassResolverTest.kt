package dev.freya02.link.server.resolution

import dev.freya02.link.server.LinkRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private const val BASE_URL = "https://docs.bc.freya02.dev"

class ClassResolverTest {

    @Test
    fun `Can resolve module classes`() {
        ClassResolver.singleClass(LinkRequest("JDAConfiguration")).also { link ->
            assertEquals("$BASE_URL/BotCommands-spring/io.github.freya022.botcommands.api.core.config/-j-d-a-configuration/index.html", link.url)
        }

        ClassResolver.singleClass(LinkRequest("BConfig")).also { link ->
            assertEquals("$BASE_URL/BotCommands-core/io.github.freya022.botcommands.api.core.config/-b-config/index.html", link.url)
        }
    }
}
