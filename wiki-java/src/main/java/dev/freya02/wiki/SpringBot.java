package dev.freya02.wiki;

import io.github.freya022.botcommands.api.core.JDAService;
import io.github.freya022.botcommands.api.core.config.JDAConfiguration;
import io.github.freya022.botcommands.api.core.events.BReadyEvent;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

// --8<-- [start:jdaservice-java]
@Service
@NullMarked // Everything is non-null unless @Nullable
public class SpringBot extends JDAService {
    private final JDAConfiguration jdaConfiguration;
    private final String token;

    public SpringBot(JDAConfiguration jdaConfiguration, @Value("${bot.token}") String token) {
        this.jdaConfiguration = jdaConfiguration;
        this.token = token;
    }

    @Override
    public Set<CacheFlag> getCacheFlags() {
        return jdaConfiguration.getCacheFlags();
    }

    @Override
    public Set<GatewayIntent> getIntents() {
        return jdaConfiguration.getIntents();
    }

    @Override
    public void createJDA(BReadyEvent event, IEventManager eventManager) {
        // This uses JDABuilder#createLight, with the intents and the additional cache flags set above
        // It also sets the EventManager and a special rate limiter
        createLight(token)
                .setActivity(Activity.customStatus("In Java with ❤️"))
                .build();
    }
}
// --8<-- [end:jdaservice-java]
