package dev.freya02.wiki.resolvers;

import io.github.freya022.botcommands.api.commands.text.BaseCommandEvent;
import io.github.freya022.botcommands.api.commands.text.options.TextCommandOption;
import io.github.freya022.botcommands.api.core.service.annotations.Resolver;
import io.github.freya022.botcommands.api.parameters.ClassParameterResolver;
import io.github.freya022.botcommands.api.parameters.resolvers.TextParameterResolver;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

// --8<-- [start:time_unit_resolver]
@Resolver
@NullMarked // Everything is non-null unless @Nullable
public class TextTimeUnitResolver
        extends ClassParameterResolver<TextTimeUnitResolver, TimeUnit>
        implements TextParameterResolver<TextTimeUnitResolver, TimeUnit> {

    public TextTimeUnitResolver() {
        super(TimeUnit.class);
    }

    @Override
    public Pattern getPattern() {
        // "(?i)" and "(?-i)" delimits a section of the pattern which is case-insensitive
        return Pattern.compile("(?i)(seconds|minutes|hours|days)(?-i)");
    }

    @Override
    public String getTestExample() {
        // A string combined with other options to check that a variation's final pattern is parse-able
        return "hOurS";
    }

    @Override
    public String getHelpExample(TextCommandOption textCommandOption, BaseCommandEvent baseCommandEvent) {
        return "hours";
    }

    @Nullable
    @Override
    public TimeUnit resolve(TextCommandOption option, MessageReceivedEvent event, String[] args) {
        // This code runs only if the pattern matched,
        // in this case, it must correspond to a valid 'TimeUnit'
        var unitStr = args[0].toUpperCase();
        return TimeUnit.valueOf(unitStr);
    }
}
// --8<-- [end:time_unit_resolver]
