# About selection menus
## How can I use them ?
You'll first need to have some experience using [JDA's SelectMenu(s)](https://jda.wiki/using-jda/interactions/#selection-menus-dropdowns) directly

You will need to have your `ComponentManager` set up in order to use the Components API, which is where the selection menus are created.

You can then use the `Components` class methods such as `Components#selectionMenu`, which is equivalent to JDA's `SelectionMenu#create`, except it will give you a builder class where you can set the properties defined in [the Components API wiki](../The-Components-API)

## How to listen to selection events

You have to make a method annotated with `#!java @JDASelectionMenuListener` and have their first parameter be a `SelectionEvent`

Example:
```java
@JDASelectionMenuListener(name = "test")
public void testSelectionMenuListener(SelectionEvent event) {
	event.reply("test: " + event.getValues()).setEphemeral(true).queue();
}
```

## More examples

You can see more examples in the [examples directory](https://github.com/freya022/BotCommands/tree/master/examples/src/main/java/com/freya02/bot/componentsbot)