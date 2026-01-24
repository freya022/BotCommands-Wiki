package dev.freya02.wiki.autocomplete.transformer;

import io.github.freya022.botcommands.api.commands.application.slash.autocomplete.AutocompleteTransformer;
import io.github.freya022.botcommands.api.core.service.annotations.BService;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;

// --8<-- [start:autocomplete_transformer-java]
@BService
public class FullNameTransformer implements AutocompleteTransformer<FullName> {
    @NotNull
    @Override
    public Class<FullName> getElementType() {
        return FullName.class;
    }

    @NotNull
    @Override
    public Command.Choice apply(@NotNull FullName fullName) {
        return new Command.Choice(
                "%s %s".formatted(fullName.firstName(), fullName.secondName()),
                "%s|%s".formatted(fullName, fullName.secondName())
        );
    }
}
// --8<-- [end:autocomplete_transformer-java]
