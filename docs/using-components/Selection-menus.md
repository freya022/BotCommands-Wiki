## About selection menus

Selection menus works almost the same as the buttons, they are made with a builder. There is currently no selection menu wiki on JDA's wiki as their usage is pretty straight forward, just look at the `SelectionMenu` docs.

# How can I use them ?
You will need to have your `ComponentManager` set up in order to use the Components API, which is where the selection menus are created.

You can then use the `Components` class methods such as `Components#selectionMenu`, which is equivalent to JDA's `SelectionMenu#create`, except it will give you a builder class where you can set the properties defined in [the Components API wiki](https://github.com/freya022/BotCommands/wiki/The-Components-API)

## How to listen to selection events

You have to make a method annotated with `@JDASelectionMenuListener` and have their first parameter be a `SelectionEvent`

Example:
```java
@JDASelectionMenuListener(name = "test")
public void testSelectionMenuListener(SelectionEvent event) {
	event.reply("test: " + event.getValues()).setEphemeral(true).queue();
}
```

## More examples

You can see more examples in the [examples directory](https://github.com/freya022/BotCommands/tree/master/examples/src/main/java/com/freya02/bot/componentsbot)