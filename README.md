# kotlin-argparser

This is a simple argument parser for kotlin.


## Usage
Here is a small example. There will be a complete documentation in the wiki.

```kotlin
val parser: Argparser = Argparser()
// define the expected arguments
parser.addArgument("simple test argument", "--arg1")
// define a default value for an argument
parser.addArgument("test argument with default", "--arg2", defaultValue = "hello world")
// define a flag
parser.addArgument("flag argument", "--flag", hasValue = false)
```

### Exceptions
The parser throws exceptions if the user passed invalid arguments / values for the argument.
You can catch them and print a message e.g.

#### NotDeclaredException
This Exception will be thrown, if you pass an argument that was not declared in the code.

#### NotAllowedValueException
The user passed a value to an argument where it is not allowed.

#### RequiredNotPassedException
A required argument was not passed to the parser.
