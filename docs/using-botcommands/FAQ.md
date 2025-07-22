# Frequently Asked Questions

## How do I get a `JDA` instance?
Since JDA is only started after your entire bot has started up, you cannot inject a `JDA` instance directly,
however, here are a few ways you can get it:

- (Recommended) By [listening](./events.md) to [[InjectedJDAEvent]] and getting the instance from it
- By injecting a [`LazyService<JDA>`][[LazyService]] then using it when you need it
- By injecting a [[BContext]] or [[ServiceContainer]] then retrieving it when you need it

If we imagine a case where JDA takes a very long time to load,
tasks that depend on it should be delayed until it is available.

For example, if you have a task you need to schedule regularly,
you should schedule it after receiving [[InjectedJDAEvent]],
this way you are guaranteed to have an instance when the scheduled task executes.
