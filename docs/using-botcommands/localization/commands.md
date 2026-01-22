# Localizing application commands

The framework allows you to localize your commands, options and choices.

This is based on JDA's [`LocalizationFunction`](https://docs.jda.wiki/net/dv8tion/jda/api/interactions/commands/localization/LocalizationFunction.html),
see the documentation on how keys are composed.

## Registering bundles

You'll have to provide the name(s) and supported locales to [[BApplicationConfigBuilder#addLocalizations]].
If a bundle does not support locales, it can still be used for default descriptions.

## Default descriptions

Commands and options can have their descriptions retrieved from "root" bundles,
essentially this lets you move the descriptions from your code into the bundles.

## Examples

=== "Set the "default" description of `/search`"

    ```json title="src/main/resources/bc_localization/Commands.json"
    {
        "search.description": "Searches the docs"
    }
    ```

    This way you don't need to set the description on `#!java @JDASlashCommand(name = "search")`.

=== "Set localized descriptions"

    ```json title="src/main/resources/bc_localization/Commands_fr_FR.json"
    {
        "tags.edit": {
            "name": "rechercher",
            "description": "Rechercher la documentation",
            "options": {
                "query": {
                    "name": "requête",
                    "description": "La requête à rechercher"
                }
            }
        }
    }
    ```
