# Responding using contexts

The framework allows you to inject contexts which allow you to get messages:

- For interactions: [[AppLocalizationContext]]
- In text commands: [[TextLocalizationContext]]

To get an instance of them, add them as a parameter of your interaction handler or text command,
and annotate them with [[LocalizationBundle]].

!!! example

    ```kotlin
    @JDASlashCommand("info")
    fun onSlashInfo(event: GuildSlashEvent, 
                    @LocalizationBundle("commands", prefix = "info") localizationContext: AppLocalizationContext) {
        // ...
    }
    ```
