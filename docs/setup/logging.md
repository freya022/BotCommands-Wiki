[set-logback-file-java]: https://github.com/freya022/BotCommands/blob/1269ca668d702d3d23eef3734eaf9c61da7de3c7/BotTemplate/BotTemplate-Java/src/main/java/io/github/freya022/bot/Main.java#L15
[set-logback-file-kotlin]: https://github.com/freya022/BotCommands/blob/1269ca668d702d3d23eef3734eaf9c61da7de3c7/BotTemplate/BotTemplate-Kotlin/src/main/kotlin/io/github/freya022/bot/Main.kt#L29

In case you are **not** using the bot template, you can add a logger with the following dependencies:

- [slf4j-api](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/latest)
- [logback-classic](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic/latest)

You can then create a `logback.xml` file, which you can put in the root of your resources (`src/main/resources`),
or in another place (such as in a config directory), as shown in the bot template ([Java][set-logback-file-java] / [Kotlin][set-logback-file-kotlin])

Here are the logback configs used in the [bot template](https://github.com/freya022/BotCommands/tree/3.X/BotTemplate):

=== "Dev config"

    ```xml title="logback-test.xml"
    --8<-- "https://raw.githubusercontent.com/freya022/BotCommands/3.X/BotTemplate/BotTemplate-Kotlin/config-template/logback-test.xml"
    ```

=== "Prod config"

    ```xml title="logback.xml"
    --8<-- "https://raw.githubusercontent.com/freya022/BotCommands/3.X/BotTemplate/BotTemplate-Kotlin/config-template/logback.xml"
    ```