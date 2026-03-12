# Event listeners & special events

## Event listeners

These are [[BEventListener]] functions which can:

- Have any name
- Be suspending
- Can get services injected
- Listen to any of JDA's events (including generic ones) and built-in framework events, as seen further below

There are several properties on [[BEventListener]], such as how it is threaded, its priority, intent checks and timeouts.

### Conditionally enabling them

As event listeners are contained in services, disabling them requires disabling the service.
You can, for example, use [[RequiredIntents]] to enable a service (and so, its listeners)
only if your bot is running with certain intents enabled.

## Lifecycle events
Here is a list of events in the order they fire, each one of them also triggers a [[BStatusChangeEvent]]:

- [[PreLoadEvent]]
- [[LoadEvent]]
- [[PostLoadEvent]]
- [[BReadyEvent]]
- [[BShutdownEvent]] (after shutdown, only a [[BStatusChangeEvent]] is fired pre-shutdown)

## JDA-related events
- [[InjectedJDAEvent]]: When a JDA instance is first captured, can be used with a blocking listener to do stuff before your bot logs in
- [[PreFirstGatewayConnectEvent]]: Fired just after `InjectedJDAEvent`
- [[FirstGuildReadyEvent]]: Fired when receiving the very first [`GuildReadyEvent`](https://docs.jda.wiki/net/dv8tion/jda/api/events/guild/GuildReadyEvent.html)
