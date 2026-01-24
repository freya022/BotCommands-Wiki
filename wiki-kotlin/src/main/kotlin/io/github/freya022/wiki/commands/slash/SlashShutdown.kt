package io.github.freya022.wiki.commands.slash

import io.github.freya022.botcommands.api.commands.annotations.Command
import io.github.freya022.wiki.switches.DevCommand

// --8<-- [start:dev_command_annotated_condition-command-kotlin]
@Command
@DevCommand // Our custom condition, this command will only exist if it passes.
class SlashShutdown {
    /* */
}
// --8<-- [end:dev_command_annotated_condition-command-kotlin]
