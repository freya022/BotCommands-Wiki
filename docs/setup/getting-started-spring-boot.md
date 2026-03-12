# Starting from scratch - Spring Boot

!!! note

    This assumes you know how to use Spring Boot.

Start by creating a regular Spring Boot project, no modules are required.

## Adding the dependencies

The only strictly necessary dependencies are the framework, the Spring support module, and, JDA:

[![](https://img.shields.io/maven-central/v/io.github.freya022/BotCommands?versionPrefix=3&label=BotCommands)](https://mvnrepository.com/artifact/io.github.freya022/BotCommands/latest)
[![](https://img.shields.io/maven-central/v/net.dv8tion/JDA?versionPrefix=5&label=JDA)](https://mvnrepository.com/artifact/net.dv8tion/JDA/latest)

!!! tip ""

    Omit the `v` prefix from the version, e.g. `5.0.0-beta.18`.

=== "Maven"

    ```xml
    <dependencies>
        ...

        <dependency>
            <groupId>net.dv8tion</groupId>
            <artifactId>JDA</artifactId>
            <version>JDA_VERSION</version>
        </dependency>
        <dependency>
            <groupId>io.github.freya022</groupId>
            <artifactId>BotCommands</artifactId>
            <version>BC_VERSION</version>
        </dependency>
        <dependency>
            <groupId>io.github.freya022</groupId>
            <artifactId>BotCommands-spring</artifactId>
            <version>BC_VERSION</version>
        </dependency>
    </dependencies>
    ```

=== "Kotlin Gradle"

    ```kotlin
    repositories {
        ...
        mavenCentral()
    }
    
    dependencies {
        ...

        implementation("net.dv8tion:JDA:JDA_VERSION")
        implementation("io.github.freya022:BotCommands:BC_VERSION")
        implementation("io.github.freya022:BotCommands-spring:BC_VERSION")
    }
    ```

!!! tip

    You can also use the [Spring developer tools](https://docs.spring.io/spring-boot/reference/using/devtools.html)
    to speed up your development cycle.

## Setting up parameter names

The framework needs to be able to read your parameter names, Kotlin users don't have to do anything,
but Java users will need to add a compiler argument, see ["Java parameter names"](parameter-names.md).

## Optional - Configure logging

The Spring Boot starter should include logging, you can further configure it, in most cases this is in `logback.xml`,
see [Spring Boot logging docs](https://docs.spring.io/spring-boot/reference/features/logging.html#features.logging.custom-log-configuration).

For example, you can set the log level for the framework to `debug` by adding `<logger name="io.github.freya022.botcommands" level="debug"/>`.

## Configuring your token

After getting your token from your bot's dashboard, you can put it in your Spring environment,
this can be an [environment variable](https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.files.env-variables) or an **untracked** `application.yaml` in the current directory, or a `config/` subdirectory,
see [Spring Boot external config docs](https://docs.spring.io/spring-boot/reference/features/external-config.html#features.external-config.files).

## Creating the main class

Add the package(s) of your application to the `scanBasePackages` value of your `#!java @SpringBootApplication`.

## Optional - Configuring the framework

Configuration of the framework is then done either by using application properties (with the prefix being either `botcommands` or `jda`),
or by implementing configurers, see the [`BConfigurer` inheritors][[BConfigurer]].

## Creating a `JDAService`

Now if you try to start your bot, you will see an error about requesting a `JDAService` instance,
this is a service which is responsible for providing (part of) the configuration of your bot,
you must also start your JDA instance in `createJDA`, let's implement it!

??? info "What is it useful for?"

    - For the framework to receive all the events, useful for command updates, uploading [application emojis](../using-botcommands/app-emojis.md) and more
    - To start the bot when everything is ready
    - To check if event listeners have the required gateway intents/cache flags for them to be fired

!!! note

    The Spring support module will also check that the gateway intents and cache flags match those configured in `JDAService`,
    so, you must put them in your environment, you will then be able to set your gateway intents and cache flags using the values provided by [[JDAConfiguration]].

=== "Kotlin"

    ```kotlin
    --8<-- "SpringBot.kt:jdaservice-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "SpringBot.java:jdaservice-java"
    ```

You can now run your bot! You should be able to run the help command, by mentioning your bot `@YourBot help`.

## Other resources

Take a look at other wiki pages, such as [Dependency injection](../using-botcommands/dependency-injection/index.md), 
[Creating slash command](../using-commands/application-commands/slash-commands/index.md)
and [Using components](../using-components.md).

### Examples

You can find examples covering parts of the framework [here](https://github.com/freya022/BotCommands/tree/3.X/BotCommands-core/src/examples).

### Getting help

Don't hesitate to join [the support server](https://discord.gg/frpCcQfvTz) if you have any question!
