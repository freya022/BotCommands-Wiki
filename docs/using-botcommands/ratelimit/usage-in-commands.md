# Using rate limiters in commands
In this page, we'll focus on slash commands, but it works the same for text commands and context menu commands.

## Annotated commands

### Using an (anonymous) rate limiter
Use [[RateLimit]] or [[Cooldown]] to define one on an application command, it will define a rate limiter automatically, but cannot be referenced anywhere else.

=== "Kotlin"
    ```kotlin
    --8<-- "commands/slash/SlashRateLimit.kt:rate_limit-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "commands/slash/SlashRateLimit.java:rate_limit-java"
    ```

#### Cooldown
A cooldown can be used as `#!java @Cooldown(5, ChronoUnit.SECONDS /* also scope and deleteOnRefill */)`.

### Using an existing rate limiter
Nothing as simple as using [[RateLimitReference]] with the `group` of a rate limiter defined by a [[RateLimitProvider]].

We'll be using the same one as in ["Registering the rate limiter"](declaring.md#registering-the-rate-limiter).

=== "Kotlin"
    ```kotlin
    --8<-- "commands/slash/SlashRateLimitExisting.kt:rate_limit_existing-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "commands/slash/SlashRateLimitExisting.java:rate_limit_existing-java"
    ```

## Declarative commands

### Using an (anonymous) rate limiter
```kotlin
--8<-- "commands/slash/SlashRateLimitDsl.kt:rate_limit-kotlin_dsl"
```

#### Cooldown
A cooldown can be used as [`cooldown(5.seconds /* also scope and deleteOnRefill */)`](https://docs.bc.freya02.dev/BotCommands-core/io.github.freya022.botcommands.api.commands.builder/cooldown.html), instead of the last `rateLimit` call.

### Using an existing rate limiter
Nothing as simple as using `rateLimitReference` with the `group` of a rate limiter defined by a [[RateLimitProvider]].

We'll be using the same one as in ["Registering the rate limiter"](declaring.md#registering-the-rate-limiter).

```kotlin
--8<-- "commands/slash/SlashRateLimitExistingDsl.kt:rate_limit_existing-kotlin_dsl"
```


## Cancelling rate limits
If your interaction does an early return, you can also return the token with [`cancelRateLimit()`][[CancellableRateLimit#cancelRateLimit]],
so the user won't get penalized for this interaction.

!!! example "Cancelling when not in a voice channel"

    === "Kotlin"
        ```kotlin
        --8<-- "commands/slash/SlashCancelledRateLimit.kt:cancelled_rate_limit-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "commands/slash/SlashCancelledRateLimit.java:cancelled_rate_limit-java"
        ```
