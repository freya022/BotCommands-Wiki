package dev.freya02.wiki.commands.slash;

import dev.freya02.wiki.config.Config;
import io.github.freya022.botcommands.api.commands.annotations.Command;
import io.github.freya022.botcommands.api.core.service.ConditionalServiceChecker;
import io.github.freya022.botcommands.api.core.service.ServiceContainer;
import io.github.freya022.botcommands.api.core.service.annotations.ConditionalService;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

// --8<-- [start:tag_interfaced_condition-java]
@Command
@NullMarked // Everything is non-null unless @Nullable
@ConditionalService(TagCommand.FeatureCheck.class) // Only create the command if this passes
public class TagCommand {
    /* */

    public static class FeatureCheck implements ConditionalServiceChecker {
        @Nullable
        @Override
        public String checkServiceAvailability(ServiceContainer serviceContainer, Class<?> checkedClass) {
            final var config = serviceContainer.getService(Config.class); // Suppose this is your configuration
            if (!config.areTagsEnabled()) {
                return "Tags are disabled in the configuration"; // Do not allow the tag command!
            }
            return null; // No error message, allow the tag command!
        }
    }
}
// --8<-- [start:tag_interfaced_condition-java]
