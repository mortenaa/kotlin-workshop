# Kotlin workshop
## Morten Nygaard Åsnes 
## Bjørn Hamre

---

# Program

 - 40 min. introduction
 - 25 min. excercises
 - 10 min. solutions

---

## About Kotlin

 - New programming language
 - Developed by Jetbrains
 - Inspired (a lot) by Scala, Groovy and others
 
## Resources
 - https://kotlinlang.org/docs/reference/

---

## Why Kotlin
 - Less boilerplate -> Less code -> Fewer bugs
 - More declarative code
 - More functional style
 - Better support for immutability
 - Null safe

---

## Kotlin compiles to 
 - Jvm
 - Javascript
 - Native
 - Android

---

## Getting started
 - Download Kotlin
 - IntelliJ has Kotlin support
 - Maven and gradle support
 - Easy to run scripts (.kts)

---

## Datatypes in Kotlin

"Everything is an object"
 - Double, Float
 - Long, Int
 - Short, Byte
 - String, Char
 - ~~Long[]~~, `Array<Long>`
 - Any, Unit, Nothing

---

## Variables/Values
var, val, type inference, == and no ';'

```kotlin
var changeableName: String = "Variable"
val finalName: String = "Value"

changeableName = "Can be changed"
//finalName = "Immutable"   -> compiler error

val typeInferred = "Compiler recognizes the String"

println("Value" == finalName)  // java: .equals()
println("Value" === finalName) // java: ==
```
---


## Strings
- Inline with $ og ${}
- Multiline

```kotlin
val firstName = "Bjørn"
val lastName = "Hamre"
val fullName = "$lastName, $firstName"
val multiLineString = """
    SELECT *
    FROM my_table
    WHERE id = :id
    -- can use " without escaping
""".trimIndent()
```

---

## Functions/methods 
 - fun keyword
 - can be top level outside class
 

```kotlin
fun squareWithBlock(n: Int): Int {
    return n * n
    //always 'return' in a block
}
fun squareOneLiner(n: Int) =  n * n
println("square(3) == ${square(3)}") //> 9

```
Note:

 - fun keyword
 - return type end of signatur
 - return type inferred in oneliners
 - placement of colon

---

## No return value
 - Will actually return an object of type Unit
 - Unit can be inferred

```kotlin
fun printer(n: Int){
    println("The number is $n")
}

fun printerWithUnit(n: Int): Unit {
    println("The number is $n")
}
```
Note:
- No return
- No =
---

## Default values
 - Function arguments can have default value

```kotlin
fun printTemperature(degrees: Double, unit: String = "Celcius") {
    println("temperature is $degrees $unit" )
}
printTemperature(37.0, "Celcius")
printTemperature(37.0)

> temperature is 37.0 Celcius
> temperature is 37.0 Celcius
```

---

## Named arguments
 - Confusing with methods having arguments of same type
 - what are the true, true arguments?

```kotlin
fun confusing(name: String, isActive: Boolean, isAdmin: Boolean){}

confusing("Ole", true, true)
confusing(name = "Ole", isAdmin = true, isActive = false)
```
Note:
When a function is called with both positional and named arguments, all the positional arguments should be placed before the first named one. For example, the call f(1, y = 2) is allowed, but f(x = 1, 2) is not.
---

## Nullable types
 - Every type has a complementary nullable type
 - String and String?
 - Compiler prevents assignment of null to "ordinary" types

```kotlin
//val middleName: String = null //will not compile
val middleName: String? = null
if (middleName != null){
    //No need to .get() as in Optional
    println("Middle name: $middleName")
}
``` 

---
## Nullsafe and Elvis
To be used in "train wrecks"
 - ?. 
 - ?:

```kotlin
val middleName: String? = null
val upperMiddleName: String? = middleName?.toUpperCase()
val defaultIfNull: String = middleName?.toUpperCase()?:""
```

---

## Smart cast
 - Any is the super class of all classes
 - No need to cast after type is asserted

```kotlin
val something: Any = getAnObject()
if ( something is String ) {
    println( something.toUpperCase() )
}

```

---

## Pattern matching
 - Notice the smart casting (is)
 
```kotlin
val surprise: Any = getSomething()
var whatIsIt: String? = null
when ( surprise ){
    is String -> whatIsIt = surprise.toUpperCase()
    42        -> whatIsIt = "Life, the universe and everything"
    3.14      -> whatIsIt = "PI"
}
```
But 'var' isn't functional !

---

## Pattern to avoid var
 - Use `when` as expression
 - Must cover all possibilities (be exhaustive)
 - can have a default part (else)

```kotlin
val surprise: Any = getSomething()
val whatIsIt: String = when ( surprise ){
    is String -> surprise.toUpperCase()
    42        -> "Life, the universe and everything"
    3.14      -> "PI"
    else      -> "Whatever"
}
```

---
## When without arguments
 - Can replace long if-else if blocks with when
 - Used as expressen -> must be exhaustive

```kotlin
val result = when {
    char == 'A' || char == 'a' -> 1
    else -> -1
}
``` 
---

## Pattern to avoid var
 - use `if` as an expression

```kotlin
val random = Random().nextInt()
val absolute = if ( random < 0 ) -random else random
```

---

## Pattern to avoid var
 - try catch as an expression
 - Implicit return

```kotlin
val value = try {
    getSomething() as String
} catch (e: Exception){
    "Not a string"
}
```
---

# Part 2
## Classes
...and friends

---
## Classes
 - Primary constructor
 - Default public
 - no `new` when instansiating

```kotlin
class Person constructor(firstName: String){
    val name = firstName.toUpperCase()
    init{
        println("firstName used in initializer blocks $firstName")
    }
}

val bjorn = Person("Bjørn")
println(bjorn.name)
```
---
## Getters and setters
Full syntax:
```kotlin
var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
```
```kotlin
class Person(firstName: String){
    var name = firstName
        get() = field.toUpperCase()
        set(value){
            field = value
        }
}

val bjorn = Person("Bjørn")
println(bjorn.name)
```
Note:
- a backing fields is generated if needed
- getter and setter are generated by default unless specified

---
## Generated getter/setter
```kotlin
class Person(firstName: String){
    val name = firstName.toUpperCase()
}
val bjorn = Person("Bjørn")
println(bjorn.name)
```
or even simpler:
```kotlin
class Person(val firstName: String)
val bjorn = Person("Bjørn")
println(bjorn.name)
```
Note: 
- val could be var

---

## Nice class features
 - Default values
 - Named arguments

```kotlin
class Person(val lname: String,
             val fname: String,
             val mname: String = ""){
    fun fullName() = "$fname $mname $lname"
}

val defaultMname = Person("Hamre", "Bjørn" )
val namedArguments = Person(
        fname = "Bjørn",
        mname = "Håkonsen",
        lname = "Hamre")

println( defaultMname.fullName() ) //> Bjørn  Hamre
println( namedArguments.fullName() ) //> Bjørn Håkonsen Hamre
```
Note:
- Default values at the end of the signature

---
## Data class
- When data matters
- immutable (if val)
- copy(), toString(), equals()

```kotlin
data class Person(
        val firstName: String,
        val lastName: String,
        val age: Int,
        val sex: String = "Not given"
)
val bjorn = Person("Bjørn", "Hamre", 46)
println(bjorn)
> Person(firstName=Bjørn, lastName=Hamre, age=46, sex=Not given)
val otherBjorn = bjorn.copy(sex = "Male", age = 29)
println(otherBjorn)
> Person(firstName=Bjørn, lastName=Hamre, age=29, sex=Male)
```
Note:
 - Can implement methods
 - Inheritance ?

---

## Access modifiers
- Properties and methods of a class can be:
   - public (default): available to all
   - private: only available from within the class
   - protected: available within the class, in sub-classes and classes in same package
   - internal: available from any code in the same module (=compiled together)

---

## Enums 

```kotlin
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
}
Color.RED.rgb
```
---

## Singletons (utility classes)
- No private constructor and hassle
- object

```kotlin
object ObjectMapperFactory{
    fun create() = configure( ObjectMapper() ) 
    fun configure(objectMapper: ObjectMapper): ObjectMapper {
        //Configure mapper
        return objectMapper
    }
}
val objectMapper = ObjectMapperFactory.create() 
```
note:
- ObjectMapperFactory object we created using the object declaration is a singleton. 
- We can’t create objects of ObjectMapperFactory — it’s not considered to be a class
   - it’s already an object. 
- Think of it like a class with a private constructor and only static methods
   - but with less code.
- "static" utility classes
---

## Companion object
 - object inside class
 - E.g. factory

```kotlin
class MyClass private constructor() {
    companion object {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create()
```

Note:
 - methods in companion object accessed with classname
 - private constructor

 - If a group of functions are high level, general, and widely useful then place them
directly within a package. 
- If on the other hand, a few functions are more closely related to each other than the other functions, like create() and configure(), are more closely related to each other than to milesToKm() ,then place them within a singleton.

---

## Tuples
 - to
 - destructuring

```kotlin
val tuple = 42 to "The meaning"
println( "tuple: $tuple")
val theSecret = tuple.first
val (secret, message) = tuple
println("secret: $secret, message: $message")
```

---

## Data class is tuple
```kotlin
data class Person(val name: String, val age: Int, val occupation: String)
val bjorn = Person("Bjørn", 46, "Programmer")
val (na, _, occ) = bjorn
println("$na is a $occ")
```
---
## Collections
 - immutable "by default"

```kotlin
val list = listOf("Bjørn", "Erik", "Thomas")
val set  = setOf("Bjørn", "Erik", "Thomas")
val map  = mapOf("B" to "Bjørn", "E" to "Erik")
val modifiedList = list + "The Bear"
println(list) //[Bjørn, Erik, Thomas]
println(modifiedList) //[Bjørn, Erik, Thomas, The Bear]
```

---

## Collections
 - can be mutable

```kotlin
val arrayList  = arrayListOf("Bjørn", "Erik", "Thomas")
val array      = arrayOf("Bjørn", "Erik", "Thomas")

val mlist = mutableListOf("Bjørn", "Erik", "Thomas")
val mset  = mutableSetOf("Bjørn", "Erik", "Thomas")
val mmap  = mutableMapOf("B" to "Bjørn", "E" to "Erik")
println(mmap)
```

//todo: .toMutable
//ikke immutable i bytekode/java
---
## Using collections
 - Like in Java
 - No .stream() or .collect()
 - Use { and }

```kotlin
data class Employee(val name: String, val salary: Long)

val employees = listOf(
    Employee("Bjørn", 1_000_000),
    Employee("Junior", 300_000),
    Employee("The Boss", 5_000_000)
)

val highSalaries: List<Long> =
    employees.filter { it.salary > 500_000 }
        .filter { emp -> emp.salary > 500_000 }
        .map { it.salary }
val average = highSalaries.average()

```

---
---

## More about classes
 - Can be abstract, sealed, open
 - Can have several constructors
 - Can implement interfaces
 - supports inheritance
 - ...whatever java classes can do
