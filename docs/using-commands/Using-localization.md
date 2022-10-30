# Using localization

Localization lets you translate commands & responses to the user's or the guild's language, all languages supported by Discord are supported by the framework, you can find a list of languages in JDA's [`DiscordLocale`](https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/interactions/DiscordLocale.html).

## Localization files
Localization can be stored in the JSON format and can contain your command translations, as well as command responses, or any other string.

### Where do I store them ?
Localization files need to be stored in the `bc_localization` folder, in your `resources` directory, 
these files can be of any *base* name, but must end with the locale's string, and must have a `.json` extension.

Examples:

* Default localization for the `MyLocalizations` bundle: `MyLocalizations.json`
* English localization for the `MyLocalizations` bundle: `MyLocalizations_en.json`
* UK localization for the `MyLocalizations` bundle: `MyLocalizations_en_GB.json`

### What do they look like ?
The JSON file is going to be an object, with each key being the "localization key" and the value being the "localization template".

The localization key is a string with keywords separated by a dot, the framework takes advantage of JSON as you can nest objects with their translations inside, the path to the nested translation will be your localization key, 
but they can look exactly like the keys in Java's `ResourceBundle`, where no nesting is required.

??? note "Example without nesting"

    ```json
    {
        "ban.name": "ban",
        "ban.description": "Bans an user"
    }
    ```

??? note "Example with nesting"

    ```json
    {
        "ban": {
            "name": "ban",
            "description": "Bans an user"
        }
    }
    ```

## Localizing default messages
Default messages are messages that can be sent by the framework itself, they can come from the command listeners, components or modals for example. 

### Replacing default messages
Default messages are defined in `/resources/bc_localization/DefaultMessages.json`, this is where you can find all the localization keys used by the framework.

To override one or more default messages, create your own `DefaultMessages.json`, like you would with a normal localization file.

!!! note "Replacing default messages"
    
    ```json title="/resources/bc_localization/DefaultMessages.json"
    {
        "general_error_message": "The bot has encountered an error, try again later."
    }
    ```

### Adding translations to default messages

Default messages can also be localized, but they must keep the same base name, i.e. `DefaultMessages`.

!!! note "Adding translations to default messages"

    ```json title="/resources/bc_localization/DefaultMessages_fr.json"
    {
        "general_error_message": "Le bot a rencontré une erreur, veuillez réessayer plus tard."
    }   
    ```

## Localizing application commands
Only application commands supports localization, translations can include names and description of commands as well as options, and also choice names.

You will need to indicate to the framework which localization files are available, and which languages they support. This can be done with `ApplicationCommandsBuilder#addLocalizations`, such as:

``` java title="Main.java"
CommandsBuilder builder = ...;
builder.applicationCommandBuilder(applicationCommandsBuilder -> applicationCommandsBuilder
        .addLocalizations("LocalizationWikiCommands", Locale.US) // (1)
);
```

1.  This enables localization from the `LocalizationWikiCommands.json` bundle, in the `en_US` language. (i.e. `LocalizationWikiCommands_en_US.json`)

    If you wish to add more localizations, add a `Locale` here, and create the corresponding files.

You can then create your commands as you would normally, no need to set up special names or anything.

Your localization keys will be the same as specified by JDA's `LocalizationFunction`, 
which means the keys are composed of the complete path, combined with the option's name and the choice's name as well, please refer to the [JDA documentation](https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/interactions/commands/localization/LocalizationFunction.html) for more details.

## Localization responses

TODO