package io.github.freya022.wiki.java;

import io.github.freya022.botcommands.api.core.JDAService;
import io.github.freya022.botcommands.api.core.config.JDAConfiguration;
import io.github.freya022.botcommands.api.core.events.BReadyEvent;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

// --8<-- [start:jdaservice-java]
@Service
public class SpringBot extends JDAService {
    private final JDAConfiguration jdaConfiguration;
    private final String token;

    public SpringBot(JDAConfiguration jdaConfiguration, @Value("${bot.token}") String token) {
        this.jdaConfiguration = jdaConfiguration;
        this.token = token;
    }

    @NotNull
    @Override
    public Set<CacheFlag> getCacheFlags() {
        return jdaConfiguration.getCacheFlags();
    }

    @NotNull
    @Override
    public Set<GatewayIntent> getIntents() {
        return jdaConfiguration.getIntents();
    }

    @Override
    public void createJDA(@NotNull BReadyEvent event, @NotNull IEventManager eventManager) {
        // This uses JDABuilder#createLight, with the intents and the additional cache flags set above
        // It also sets the EventManager and a special rate limiter
        createLight(token)
                .setActivity(Activity.customStatus("In Java with ❤️"))
                .build();
    }
}
// --8<-- [end:jdaservice-java]
