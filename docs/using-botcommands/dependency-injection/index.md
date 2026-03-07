# Dependency injection

Dependency injection provided by this framework is a more lightweight alternative to dedicated frameworks,
quite similarly to [Spring](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html) (which is supported) 
or [CDI using Weld](https://www.baeldung.com/java-ee-cdi).

Rather than you having to construct objects, you may only request them,
the framework will then construct it by providing the dependencies required for your service, wherever they may come from.

This avoids having to pass objects everywhere, allowing a more effective decoupling,
and allows switching implementations in a completely transparent manner.

!!! question "How do I know if I can inject something?"

    Objects like [[BContext]], [[Buttons]], [[Modals]] or [[EventWaiter]] are typically annotated with either [[BService]] or [[InterfacedService]].

    - `@BService` declares a service that can be injected anywhere. It can be followed by conditional annotations, making it unavailable if the conditions fail.
      
        This is the case with `Buttons`, the annotated class is the one you can request.

    - `@InterfacedService` says that any subclass implementing the interface will be injectable as such.

        This is the case with `BContext`, requesting one will give you an implementation of that interface, if found.

    If you see one of them, you can put a parameter in your class's constructor to request it.

!!! example

    `ConnectionSupplier` is an interfaced service (an interface that, when implemented, enables the service to be retrieved as such interface).

    You can create an implementation of this interface, per database, enabling you to switch your database, 
    for example, using a configuration file, without changing anything else in your code.
