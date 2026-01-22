# Using modals

Modals provided by the framework are your usual Discord modals,
they are created from the [[Modals]] factory, 
which you can inject like any other service.

!!! tip

    You can use these modals and non-BC modals at the same time.

Modals can:

- Have timeouts
    - The default is set in [[Modals#defaultTimeout]]
- Have method handlers or callbacks

!!! tip "Disabling classes depending on modals"

    You can use [[RequiresModals]] if you want your class to be disabled when modals are not available.

## Method modal handlers
You can bind a modal to a handler similar to those used by persistent components, however,
these aren't usable after a restart.

### Creating the handler
Create a method annotated with [[ModalHandler]], with the first parameter being a [[ModalEvent]].

### Passing data
If you want to pass some values from your interaction which replied with the modal,
you will need to declare parameters to retrieve them with [[ModalData]].

### Getting modal input values
To get the inputs, add parameters annotated with [[ModalInput]],
you can see all the supported types in [[ModalParameterResolver]].

### Binding to the handler method
Finally, you can bind your modal to the method, here's a full example:

=== "Kotlin"
    ```kotlin
    --8<-- "wiki/commands/slash/SlashReport.kt:report-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "wiki/java/commands/slash/SlashReport.java:report-java"
    ```

## Lambda modal handlers
You can also use a lambda, similar to ephemeral components, to handle modal events in the same context as it was declared in.

The input values can be retrieved using [[ModalEvent#get]] (`getValue` in Java),
passing the original input (`TextInput`, `StringSelectMenu`...).

You can also use the custom IDs set in the inputs, with [`ModalEvent#getValue`](https://docs.jda.wiki/net/dv8tion/jda/api/interactions/modals/ModalInteraction.html#getValue(java.lang.String)).

## Full example

=== "Kotlin"
    ```kotlin
    --8<-- "wiki/commands/slash/SlashRequestRole.kt:request_role-kotlin"
    ```

=== "Java"
    ```java
    --8<-- "wiki/java/commands/slash/SlashRequestRole.java:request_role-java"
    ```
