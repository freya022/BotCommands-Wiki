package dev.freya02.wiki.resolvers

import io.github.freya022.botcommands.api.commands.text.BaseCommandEvent
import io.github.freya022.botcommands.api.commands.text.options.TextCommandOption
import io.github.freya022.botcommands.api.core.service.annotations.Resolver
import io.github.freya022.botcommands.api.parameters.ClassParameterResolver
import io.github.freya022.botcommands.api.parameters.resolvers.TextParameterResolver
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

// --8<-- [start:time_unit_resolver]
@Resolver
class TextTimeUnitResolver : ClassParameterResolver<TextTimeUnitResolver, TimeUnit>(TimeUnit::class),
                             TextParameterResolver<TextTimeUnitResolver, TimeUnit> {

    // "(?i)" and "(?-i)" delimits a section of the pattern which is case-insensitive
    override val pattern: Pattern = Pattern.compile("(?i)(seconds|minutes|hours|days)(?-i)")
    // A string combined with other options to check that a variation's final pattern is parse-able
    override val testExample: String = "hOurS"

    override fun getHelpExample(option: TextCommandOption, event: BaseCommandEvent): String {
        return "hours"
    }

    override suspend fun resolveSuspend(
        option: TextCommandOption,
        event: MessageReceivedEvent,
        args: Array<String?>,
    ): TimeUnit {
        // This code runs only if the pattern matched,
        // in this case, it must correspond to a valid 'TimeUnit'
        val unitStr = args[0]!!.uppercase()
        return TimeUnit.valueOf(unitStr)
    }
}
// --8<-- [end:time_unit_resolver]
