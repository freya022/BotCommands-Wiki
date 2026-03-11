package dev.freya02.wiki.commands.text;

import io.github.freya022.botcommands.api.commands.CommandPath;
import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.commands.annotations.GeneratedOption;
import io.github.freya022.botcommands.api.commands.text.CommandEvent;
import io.github.freya022.botcommands.api.commands.text.TextGeneratedValueSupplier;
import io.github.freya022.botcommands.api.commands.text.TextGeneratedValueSupplierProvider;
import io.github.freya022.botcommands.api.commands.text.annotations.JDATextCommandVariation;
import io.github.freya022.botcommands.api.core.reflect.ParameterType;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.jspecify.annotations.NullMarked;

import java.time.Instant;

// --8<-- [start:create_time-java]
@Command
@NullMarked // Everything is non-null unless @Nullable
public class TextCreateTime implements TextGeneratedValueSupplierProvider {
    @Override
    public TextGeneratedValueSupplier getGeneratedValueSupplier(CommandPath commandPath, String optionName, ParameterType parameterType) {
        if (commandPath.getName().equals("create_time")) {
            if (optionName.equals("timestamp")) {
                // Create a snapshot of the instant the command was created
                final Instant now = Instant.now();
                // Give back the instant snapshot, as this will be called every time the command runs
                return event -> now;
            }
        }

        throw new IllegalArgumentException("Unknown generated option: " + optionName);
    }

    @JDATextCommandVariation(path = "create_time", description = "Shows the creation time of this command")
    public void onTextCreateTime(
            CommandEvent event,
            @GeneratedOption Instant timestamp
    ) {
        event.reply("I was created on " + TimeFormat.DATE_TIME_SHORT.format(timestamp)).queue();
    }
}
// --8<-- [end:create_time-java]
