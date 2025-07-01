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

??? tip "Kotlin - Using a custom `CoroutineEventManager`"

    I recommend creating a custom `CoroutineEventManager`,
    that way you can configure the amount of threads or their names,
    which may be convenient in logs.

    You can do so by implementing a `ICoroutineEventManagerSupplier` service, 
    with the help of `namedDefaultScope`:
    ```kotlin
    --8<-- "wiki/CoroutineEventManagerSupplier.kt:coroutine_event_manager_supplier-kotlin"
    ```

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
    --8<-- "wiki/SpringBot.kt:jdaservice-kotlin"
    ```

=== "Java"

    ```java
    --8<-- "wiki/java/SpringBot.java:jdaservice-java"
    ```

You can now run your bot! You should be able to run the help command, by mentioning your bot `@YourBot help`.

!!! tip

    If necessary, you can retrieve a `JDA` instance once `createJDA` has been called, 
    I recommend listening to `InjectedJDAEvent`, but you can also get one later, using `BContext#jda`.

## Optional - Add `stacktrace-decoroutinator`

I recommend adding [`stacktrace-decoroutinator`](https://github.com/Anamorphosee/stacktrace-decoroutinator), 
which will help you get clearer stacktrace when using Kotlin coroutines.

!!! note

    Java users also benefit from it as it may help debug framework issues.

[![](https://img.shields.io/maven-central/v/dev.reformator.stacktracedecoroutinator/stacktrace-decoroutinator-jvm?label=stacktrace-decoroutinator)](https://mvnrepository.com/artifact/dev.reformator.stacktracedecoroutinator/stacktrace-decoroutinator-jvm/latest)

=== "Maven"

    ```xml
    <dependencies>
        ...

        <dependency>
            <groupId>dev.reformator.stacktracedecoroutinator</groupId>
            <artifactId>stacktrace-decoroutinator-jvm</artifactId>
            <version>SD_VERSION</version>
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

        implementation("dev.reformator.stacktracedecoroutinator:stacktrace-decoroutinator-jvm:SD_VERSION")
    }
    ```

Finally, load it on the first lines of your main program:

=== "Kotlin"

    ```kotlin
    // stacktrace-decoroutinator has issues when reloading with hotswap agent
    if ("-XX:+AllowEnhancedClassRedefinition" in ManagementFactory.getRuntimeMXBean().inputArguments) {
        logger.info { "Skipping stacktrace-decoroutinator as enhanced hotswap is active" }
    } else if ("--no-decoroutinator" in args) {
        logger.info { "Skipping stacktrace-decoroutinator as --no-decoroutinator is specified" }
    } else {
        DecoroutinatorRuntime.load()
    }
    ```

    !!! warning

        `stacktrace-decoroutinator` must be loaded before any coroutine code is loaded, 
        including suspending main functions `suspend fun main() { ... }`.

=== "Java"

    ```java
    // stacktrace-decoroutinator has issues when reloading with hotswap agent
    if (ManagementFactory.getRuntimeMXBean().getInputArguments().contains("-XX:+AllowEnhancedClassRedefinition")) {
        logger.info("Skipping stacktrace-decoroutinator as enhanced hotswap is active");
    } else if (Arrays.asList(args).contains("--no-decoroutinator")) {
        logger.info("Skipping stacktrace-decoroutinator as --no-decoroutinator is specified");
    } else {
        DecoroutinatorRuntime.INSTANCE.load();
    }
    ```

## Other resources

Take a look at other wiki pages, such as [Dependency injection](../using-botcommands/dependency-injection/index.md), 
[Creating slash command](../using-commands/application-commands/writing-slash-commands.md)
and [Using components](../using-components.md).

### Examples

You can find examples covering parts of the framework [here](https://github.com/freya022/BotCommands/tree/3.X/src/examples).

### Getting help

Don't hesitate to join [the support server](https://discord.gg/frpCcQfvTz) if you have any question!
