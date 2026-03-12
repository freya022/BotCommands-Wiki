In addition to the [`BotCommands-jda-ktx`](https://github.com/freya022/BotCommands/tree/3.X/BotCommands-jda-ktx) module offers,
Kotlin users have access to top-level functions and extensions in various categories:

## Resolvers
??? example "[[enumResolver]] — Creates a parameter resolver which transforms arguments into an enum entry, compatible with most handlers"

    ```kotlin
    enum class MyEnum {
        FIRST,
        SECOND,
        THIRD
    }
    
    @BConfiguration
    class MyEnumResolverProvider {
        // Creates an enum resolver for all values
        // you can also customize what values can be used, per-guild,
        // and also change how they are displayed
        @Resolver
        fun myEnumResolver() = enumResolver<MyEnum>()
    }
    ```

??? example "[[resolverFactory]] — Creates a factory for parameter resolvers, useful to avoid the boilerplate of using [[TypedParameterResolverFactory]]"

    See example on [the docs][[ResolverFactory]]

## I/O
??? example "[[readResource]] — Gets an `InputStream` of a resource from the classpath"

    ```kotlin
    readResource("/file.txt").use { contentStream ->
        // ...
    }
    ```

??? example "[[readResourceAsString]] — Gets a resource from the classpath as a `String`"

    ```kotlin
    val content = readResourceAsString("/file.txt")
    ```

??? example "[[withResource]] — Uses an `InputStream` of a resource from the classpath"

    ```kotlin
    withResource("/file.txt") { contentStream ->
        // ...
    }
    ```

## Coroutines
??? example "[[namedDefaultScope]] — Creates a `CoroutineScope` with a thread name and a fixed thread pool"

    ```kotlin
    // 1 thread named "[feature] timeout"
    // You can also configure other CoroutineScope characteristics
    private val timeoutScope = namedDefaultScope("[feature] timeout", corePoolSize = 1)

    // ...

    timeoutScope.launch {
        // Async task
    }
    ```

## Logging
??? example "[[loggerOf]] — Creates a logger targeting the specified class"

    ```kotlin
    private val logger = KotlinLogging.loggerOf<MyService>()

    @BService
    class MyServiceImpl : MyService {
        // ...
    }
    ```

## Collections
- [[enumSetOf]] — Creates an enum set of the provided type
- [[enumSetOfAll]] — Creates an enum set of the provided type, with all the entries in it
- [[enumMapOf]] — Creates a map with an enum key
- [[toImmutableList]] — Creates an _immutable copy_ of the list
- [[toImmutableSet]] — Creates an _immutable copy_ of the set
- [[toImmutableMap]] — Creates an _immutable copy_ of the map
- [[containsAny]] — Checks if the collection contains any of the provided elements

## Emojis
- [[asUnicodeEmoji]] — Converts a JEmoji's `Emoji` into a JDA `UnicodeEmoji`
- [[lazyUnicodeEmoji]] — Lazily fetches the Unicode from the given shortcode
