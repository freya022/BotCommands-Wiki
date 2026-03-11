package dev.freya02.wiki.commands.text

import dev.freya02.botcommands.jda.ktx.coroutines.await
import io.github.freya022.botcommands.api.commands.CommandPath
import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.botcommands.api.commands.annotations.GeneratedOption
import io.github.freya022.botcommands.api.commands.text.CommandEvent
import io.github.freya022.botcommands.api.commands.text.TextGeneratedValueSupplier
import io.github.freya022.botcommands.api.commands.text.TextGeneratedValueSupplierProvider
import io.github.freya022.botcommands.api.commands.text.annotations.JDATextCommandVariation
import io.github.freya022.botcommands.api.core.reflect.ParameterType
import net.dv8tion.jda.api.utils.TimeFormat
import java.time.Instant

// --8<-- [start:create_time-kotlin]
@Command
class TextCreateTime : TextGeneratedValueSupplierProvider {

    override fun getGeneratedValueSupplier(
        commandPath: CommandPath,
        optionName: String,
        parameterType: ParameterType
    ): TextGeneratedValueSupplier {
        if (commandPath.name == "create_time") {
            if (optionName == "timestamp") {
                // Create a snapshot of the instant the command was created
                val now = Instant.now()
                // Give back the instant snapshot, as this will be called every time the command runs
                return TextGeneratedValueSupplier { now }
            }
        }

        error("Unknown generated option: $optionName")
    }

    @JDATextCommandVariation(path = ["create_time"], description = "Shows the creation time of this command")
    suspend fun onTextCreateTime(
        event: CommandEvent,
        @GeneratedOption timestamp: Instant
    ) {
        event.reply("I was created on ${TimeFormat.DATE_TIME_SHORT.format(timestamp)}").await()
    }
}
// --8<-- [end:create_time-kotlin]
