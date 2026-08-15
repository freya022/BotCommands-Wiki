package dev.freya02.link.server.resolution

import dev.freya02.link.server.LinkException
import dev.freya02.link.server.LinkRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `Fails on ambiguous class name`() {
        assertThrows<LinkException> {
            ClassResolver.singleClass(LinkRequest("Filter"))
        }
    }

    @Test
    fun `Can resolve classes with package prefix`() {
        ClassResolver.singleClass(LinkRequest("a.Filter")).also { link ->
            assertEquals("$BASE_URL/BotCommands-core/io.github.freya022.botcommands.api.commands.annotations/-filter/index.html", link.url)
        }

        // Check for
        //  core -> api.core -> botcommands.api.core...
        val pkgParts = "io.github.freya022.botcommands.api.core".split('.')
        val combinations = buildList {
            val curr = arrayListOf<String>()

            for (part in pkgParts.reversed()) {
                curr.add(0, part)
                add(curr.joinToString("."))
            }
        }
        for (shortPkg in combinations) {
            ClassResolver.singleClass(LinkRequest("$shortPkg.Filter")).also { link ->
                assertEquals("$BASE_URL/BotCommands-core/io.github.freya022.botcommands.api.core/-filter/index.html", link.url)
            }
        }
    }
}
