package io.github.freya022.wiki.java.switches;

import io.github.freya022.botcommands.api.core.service.CustomConditionChecker;
import io.github.freya022.botcommands.api.core.service.ServiceContainer;
import io.github.freya022.wiki.config.Config;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// --8<-- [start:dev_command_annotated_condition-checker-java]
// Checks services annotated with @DevCommand
public class DevCommandChecker implements CustomConditionChecker<DevCommand> {
    @NotNull
    @Override
    public Class<DevCommand> getAnnotationType() {
        return DevCommand.class;
    }

    @Nullable
    @Override
    public String checkServiceAvailability(@NotNull ServiceContainer serviceContainer, @NotNull Class<?> checkedClass, @NotNull DevCommand annotation) {
        final var config = serviceContainer.getService(Config.class); // Suppose this is your configuration
        if (!config.isDevModeEnabled()) {
            return "Dev mode is disable in the configuration"; // Do not allow the dev commands!
        }
        return null; // No error message, allow the tag command!
    }
}
// --8<-- [end:dev_command_annotated_condition-checker-java]
