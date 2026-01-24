package dev.freya02.wiki.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.freya022.botcommands.api.core.BContext;
import io.github.freya022.botcommands.api.core.Logging;
import io.github.freya022.botcommands.api.localization.LocalizationMapRequest;
import io.github.freya022.botcommands.api.localization.readers.AbstractJacksonLocalizationMapReader;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

// --8<-- [start:local_localization_map_reader-java]
public class LocalLocalizationMapReader extends AbstractJacksonLocalizationMapReader {
    private static final Logger LOGGER = Logging.getLogger();

    private final Path basePath;

    public LocalLocalizationMapReader(@NonNull BContext context, @NonNull ObjectMapper mapper, @NonNull Path basePath) {
        super(context, mapper);
        this.basePath = basePath;
    }

    @Nullable
    @Override
    public InputStream getInputStream(@NonNull LocalizationMapRequest request) {
        var fileName = basePath + ".json";
        var path = basePath.resolve(fileName);
        if (Files.notExists(path)) {
            LOGGER.trace("Found no bundle at {}", path);
            return null;
        }

        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
// --8<-- [end:local_localization_map_reader-java]
