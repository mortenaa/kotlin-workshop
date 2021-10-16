---
marp: true
---

# Part 3
* More on classes

---

# Multiple constructors
```kotlin
class Temperature(val value: Double, val unit: String) {
    constructor(value: Double) : this(value, "Celsius")

    override fun toString() = "Temperature is $value $unit"
}

println(Temperature(17.5, "Fahrenheit"))
println(Temperature(21.5))

Temperature is 17.5 Fahrenheit
Temperature is 21.5 Celsius
```
<!--
Rotete med mange konstruktører.
Alternativ for mixed Java - Kotlin-prosjekter
Should always try to reduce the number of constructors.
-->
---
# init {}
* Executed on creation, after contructor
* Set up and initialization
* Validation
```kotlin
data class Person(
    val firstName: String, 
    val middleName: String? = null, 
    val lastName: String) {

    val fullName: String

    init {
        require(firstName.length > 0) { "Firstname cannot be empty" }
        require(lastName.length > 0) { "Lastname cannot be empty" }
        fullName = "$firstName ${middleName?:""} $lastName"
    }
}
```
---
# Companion (creational pattern)
```kotlin
class Temperature(val value: Double, val unit: String = "Celsius") {
    override fun toString() = "Temperature is $value $unit"

    companion object{
        fun of(value: Double) = Temperature(value)
    }
}
```
```java
//From Java:
Temperature.Companion.of(3.5));
```
<!-- .Companion gir singletonen -->

---
# @JvmStatic
* Static in byte code
```kotlin
class Temperature(val value: Double, val unit: String = "Celsius") {
    override fun toString() = "Temperature is $value $unit"

    companion object{
        @JvmStatic fun of(value: Double) = Temperature(value)
    }
}
```
```java
//From Java
Temperature.of(3.5));
```
---

# More about classes
* Abstract
* open
* Sealed
---

# Abstract classes
* `abstract` keyword
*  abstract method(s) without implementation
```kotlin
abstract class Developer(val name: String){
    abstract fun expertice(): String
}

class Hipster(name: String) : Developer(name) {
    override fun expertice(): String = "Frontend"
}

class Dinosaur(name: String) : Developer(name) {
    override fun expertice(): String = "Backend"
}
```

---

# Default 'closed'
* Cannot be sub-classed
* Use `open` to allow sub-classes
* Beware frameworks that proxy classes
```kotlin
open class CanBeSubclassed(){}
class Subclass(): CanBeSubclassed(){}
```
<!--
    Spring framework and hibernate proxies classes, and mutst thus be open.
    Often handled by special compiler plugins.
 -->
---
# Sealed class (and interface)
* Restricts possible subclasses 
* All subclasses must be known compile time
* Sealed class is abstract
* Can have abstract methods
```kotlin
sealed class Option<T>
class None<T>(): Option<T>()
class Some<T>(val value: T) : Option<T>()

val maybeSomething: Option<Int> = TODO()
when (maybeSomething){
    is None -> println("Nothing")
    is Some -> println("value=${maybeSomething.value}")
}
```
<!-- 
    Compiler knows when the matching is exhaustive.
    Feature introduced in Java 16
-->
---
# Extensions
* Functions
* Nullable
* Properties
* Companion object

<!-- 
Kotlin provides the ability to extend a class with new functionality without having to inherit from the class or use design patterns such as Decorator. This is done via special declarations called extensions.

For example, you can write new functions for a class from a third-party library that you can't modify. Such functions can be called in the usual way, as if they were methods of the original class. This mechanism is called an extension function. There are also extension properties that let you define new properties for existing classes.-->
---

# Extension functions
* Adding a square() function to Int
```kotlin
fun Int.square() = this * this
```
Receiver class Int gets a square function
```kotlin
val n = 43
val nSquared = n.square()
println("$n * $n == $nSquared")
> 43 * 43 == 1849
```
---

# Extend a nullable type
Can extend a nullable type to handle null values
```kotlin
fun Int.square() = this * this
fun Int?.square(): Int {
    if (this == null) return -42
    // after the null check, 'this' is autocast to a non-null type, so the square() below
    // resolves to the non-null square
    return this.square()
}
val nullInt: Int? = null
println(nullInt.square())
println(4.square())
> -42
> 16
```
---

# Extension properties
* Can add properties to classes
* lastIndex

```kotlin
val <T> List<T>.lastIndex: Int
    get() = size - 1
val numbers = listOf(1, 2, 3, 4)
println("last index is ${numbers.lastIndex}")
> last index is 3
```    
---

# Extending companion object
**If** class has a companion object it can be extended


```kotlin
class MyClass {
    companion object { }  // will be called "Companion"
}

fun MyClass.Companion.printCompanion() { println("companion") }

MyClass.printCompanion()

> companion
```
<!-- 
Compilation error if class does not defina a companion object
-->

---

# What is printed?
* fun getName() extension
```kotlin
open class Shape
class Rectangle: Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

printClassName(Rectangle())
```
---
# Static resolution
Extension functions called bases on type of reference not type of actual object.
* s in printClassName is Shape
```kotlin
open class Shape
class Rectangle: Shape()

fun Shape.getName() = "Shape"
fun Rectangle.getName() = "Rectangle"

fun printClassName(s: Shape) {
    println(s.getName())
}

printClassName(Rectangle())

> Shape
```

<!-- 
Extensions do not actually modify the classes they extend. By defining an extension, you are not inserting new members into a class, only making new functions callable with the dot-notation on variables of this type.
-->

---
# What is written?
```kotlin
class Example {
    fun printFunctionType() { println("Class method") }
}

fun Example.printFunctionType() { println("Extension function") }

Example().printFunctionType()
```
---
# Class member "always win"
```kotlin
class Example {
    fun printFunctionType() { println("Class method") }
}

fun Example.printFunctionType() { println("Extension function") }

Example().printFunctionType()

> Class method
```
---
---

# Day 2
## Part 3

- Pair and Triple
- Objectt

---
## Tuples/Pair
 - `to` / Pair
 - destructuring

```kotlin
val tuple = 42 to "The meaning"
val pair = Pair(42, "The meaning")
val theSecret = tuple.first
val theMeaning = tuple.second

val (theSecret, theMeaning) = tuple
```

<!--
Note:
- to er en infix funksjon som returnerer et Pair
- kan også lage egne infix funksjoner (og operator overloading) men det kommer vi ikke inn på her
-->

---

## Data class is tuple
- Can be destructured
- `_` for unused values
- Order is important

```kotlin
data class Person(val name: String, 
                  val age: Int, 
                  val occupation: String)
val bjorn = Person("Bjørn", 46, "Programmer")
val (na, _, occ) = bjorn
```
<!--
Note:
- destucturing virker for klasser som implementerer component1, component2...
- lister implementerer component1..5
- data classer for alle properties
- men blir feil om man endrer på rekkefølgen!
-->

---

## object / singleton
- https://csharpindepth.com/articles/Singleton
- No constructor
- No private constructor and hassle
- Replaces static utility classes in Java

```kotlin
object Utilities {
    fun toUpper(text: String) = text.toUpperCase()
}

val upperCase = Utilities.toUpper("Bjørn")
```
<!--
note:
- Utililites object we created using the object declaration is a singleton. 
- We can’t create objects of Utilities — it’s not considered to be a class
   - it’s already an object.
- Think of it like a class with a private constructor and only static methods
   - but with less code.
- "static" utility classes
- Not static in the byte code
-->

---

## Companion object
 - object inside class
 - shared between instances of the class
 - Eg. Factory

```kotlin
class MyClass private constructor() {
    companion object {
        fun create(): MyClass = MyClass()
        private val LOG = LoggerFactory.getLogger("MyClass")
    }
}
val instance = MyClass.create()
```

<!--
Note:
 - methods in companion object accessed with classname in Kotlin
 - private constructor

 - If a group of functions are high level, general, and widely useful then place them
directly within a package. 
- If on the other hand, a few functions are more closely related to each other than the other functions, like create() and configure(), are more closely related to each other than to milesToKm() ,then place them within a singleton.
- Static methods in Java
-->

---



---
# Mixing Java and Kotlin
* Named arguments when instansiating Java classes
---
# Getters and setters
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

<!--
Note:
- a backing fields is generated if needed
- getter and setter are generated by default unless specified
-->

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

<!-- 
Note: 
- val could be var
-->

---

## Access modifiers
- Properties and methods of a class can be:
   - public (default): available to all
   - private: only available from within the class
   - protected: available within the class, in sub-classes and classes in same package
   - internal: available from any code in the same module (=compiled together)


--- 
 
## Resources
 - https://github.com/javaguruen/kotlin-workshop
 - https://kotlinlang.org/docs/reference/
 - https://play.kotlinlang.org/koans/overview
 - https://www.coursera.org/learn/kotlin-for-java-developers
 - https://surveys.jetbrains.com/s3/kotlin-slack-sign-up

<!--
Note:
Spesifikasjon, stdlib doc, tutorials++ på kotlinlang
Kotlin Koans kan løses online eller med EduTools plugin i intellij, den brukes også til å løse oppgaver
i Coursera kurset.
-->
---
# Exercises - Part 4
**https://github.com/javaguruen/kotlin-workshop**


---
# Scoping functions
* let
* with
* apply
* run
* also

---
# let
* Easier to read than `if (obj != null){}` 
* Last statement returned
```kotlin
var canBeNull: String? = "Something"

canBeNull?.let{ 
    println("Not null: $it")
}

val upper = canBeNull?.let{ st ->
    st.uppercase()
}

```
---
# Many operations on same object
* Receiver is parameter (it)
```kotlin
val alice = Person("Alice", 20, "Amsterdam")
println(alice)
alice.moveTo("London")
alice.incrementAge()
println(alice)
```
```kotlin
Person("Alice", 20, "Amsterdam").let {
    println(it)
    it.moveTo("London")
    it.incrementAge()
    println(it)
}
```

<!-- Legg merke til at objektet aksesseres med it -->
---
# with
* Multiple operations on same object
* Receiver is the object itself (this)
* Last statement returned
```kotlin
val numbers = mutableListOf("one", "two", "three")
val w = with(numbers) {
    val firstItem = first()
    val lastItem = last()
    println("Size: $size, First item: $firstItem, last item: $lastItem")
    42
}
println (w)
> 42
```

<!-- Jobber på insiden av objektet selv -->

---
# apply
* Multiple assignments on same object
* The object is returned
* Object configuration
```kotlin
val adam = Person("Adam").apply {
    age = 32
    city = "London"        
}
println(adam)
```

<!-- 
Konfigurasjon? Påkrevde verdier i konstruktør, default values på resten, kan settes enkeltvis 
Objektet returneres (ikke siste uttrykk i blokken)
-->
---
# run
* Extension on "receiver object"
* Can use it's methods without reference
  * as an internal function
* Last statement returned  
```kotlin
val str = "Hello"
// this
val lastStatement = str.run {
    println("The receiver string length: $length")
    //println("The receiver string length: ${this.length}") // does the same
}
```    
<!-- Ligner veldig på with, men kan brukes med null-safe operator -->
---
# also
* Takes the object as niput
* Returns the object
```kotlin
val two = listOf(1, 2, 3, 4, 5)
    .filter { it % 2 == 0 }
    .also { println(it) }
    .first()
println(two)
```
<!--
Bruker det i midten av listeoperasjoner hvor jeg ikke ønsker å avbryte strømmen.
-->

