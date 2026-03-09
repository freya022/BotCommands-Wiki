# Declaring rate limiters globally

Rate limiters can be declared in any service class, they can be referenced anywhere and can be customized.

They can be defined by implementing [[RateLimitProvider]],
the overridden function will run before registering commands, so you can use them anywhere.

## Defining bucket configurations

Bucket configurations define limits, there are some predefined functions from [[Buckets]] which will help us,
but you can also use a [`BucketConfiguration` builder](https://bucket4j.com/8.14.0/toc.html#bucket-bonfiguration).

!!! example "Allow 5 uses per hour, but also 2 uses in 2 minutes (to prevent bursts)"

    === "Kotlin"
        ```kotlin
--8<-- "ratelimit/WikiRateLimitProvider.kt:bucket_configuration-kotlin"
        ```

    === "Java"
        ```java
--8<-- "ratelimit/WikiRateLimitProvider.java:bucket_configuration-java"
        ```

!!! info

    A cooldown is a bucket with a single limit, which has a single token and is regenerated after the cooldown time. 

## Supplying bucket configurations

The framework requests a [[BucketConfigurationSupplier]], allowing you to create buckets on a per-request basis.

!!! note

    By [default][[InMemoryBucketAccessor]], a bucket is cached depending on which scope the rate limit applies to,
    so the supplier won't run on all requests, you can use change that with a custom [[BucketAccessor]].

However, for most cases, you'll use the same configuration,
use [[BucketConfigurationSupplier#constant]] (or the [`BucketConfiguration.toSupplier()`][[toSupplier]] extension for Kotlin users)
to make a supplier that returns the same configuration.

## Creating a rate limiter

### Default rate limiters

The [default rate limiters][[RateLimiter.Companion]] should provide you ready-made implementations for both in-memory and proxied buckets,
refer to the example attached to them.

!!! example "Per-user rate limiter using the previous configuration"

    === "Kotlin"
        ```kotlin
--8<-- "ratelimit/WikiRateLimitProvider.kt:rate_limiter-kotlin"
        ```

    === "Java"
        ```java
--8<-- "ratelimit/WikiRateLimitProvider.java:rate_limiter-java"
        ```

!!! info

    You can also create a persistent bucket with [[RateLimiter#createDefaultProxied]].

### Custom rate limiter

A custom [[RateLimiter]] can also be created, which is the combination of:

- [[BucketAccessor]]: Retrieves a `Bucket` based on the context
- [[RateLimitHandler]]: Handles when an interaction has been rate limited (often to tell the user about it)

!!! tip

    When making a custom rate limiter, you can delegate one of the default implementations to avoid reimplementing existing behavior.

You can also use [[BucketKeySupplier]] to help you define functions
returning a bucket key (an identifier basically), based on the execution context.

## Registering the rate limiter
You can now register using [`RateLimitManager#rateLimit(String, RateLimiter)`][[RateLimitManager#rateLimit]],
the `group` (name of the rate limiter) must be unique.

!!! example "Full example"

    === "Kotlin"
        ```kotlin
        --8<-- "ratelimit/WikiRateLimitProvider.kt:rate_limit_provider-kotlin"
        ```

    === "Java"
        ```java
        --8<-- "ratelimit/WikiRateLimitProvider.java:rate_limit_provider-java"
        ```
