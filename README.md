# Violake

Utilities that simplify writing android applications using reactive streams.

## Features

 * Simple to use: See the examples.
 * Write parts of your application without android dependencies (the application core): This simplifies testing and code reuse.
 * The only dependencies you need for your application core are `violake-core` and ReactiveStreams from [http://www.reactive-streams.org/](http://www.reactive-streams.org/)

## Example

See `/example`.

### Screens

![example screens](https://thumbs.gfycat.com/OblongAmbitiousDuiker-small.gif "Example Screens")

# TODO

 * Much...
 
# Questions and Answers

## Data from view is not observable?

Why do you use publisher when providing data to GUI but do not use publishers when getting data from a view?

For example set text:

```java
public final class SetText implements Applicator<TextView, CharSequence> {
    // <...>
}
```

You usually apply text like this:

```java
Publisher<String> textPublisher = /* ... */;
apply(SetText.get(), view, publisher);
```

... but when getting the text you provide a function:

```java
  void receiveChangedText(String text) {
    
    
  }
  
  apply(GetText.get(), view, this::receiveChangedText);  
```

**Answer**

TODO