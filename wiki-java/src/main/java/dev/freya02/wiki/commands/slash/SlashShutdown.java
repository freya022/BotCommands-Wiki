package dev.freya02.wiki.commands.slash;

import dev.freya02.wiki.switches.DevCommand;
import io.github.freya022.botcommands.api.commands.annotations.Command;

// --8<-- [start:dev_command_annotated_condition-command-java]
@Command
@DevCommand // Our custom condition, this command will only exist if it passes.
public class SlashShutdown {
    /* */
}
// --8<-- [end:dev_command_annotated_condition-command-java]
