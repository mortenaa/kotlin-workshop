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
 - easy to run scripts (.kts)

---

## Datatypes in Kotlin
"Everything is an object"
 - Double, Float
 - Long, Int
 - Short, Byte
 - String, Char
 - ~~Long[]~~, `Array<Long>`
 - Any, Unit, nothing

---

## Variables/Values
var, val, type inference, == and no ';'

```kotlin
var changeableName = "Variable"
val finalName = "Value"
changeableName = "Can be changed"
//finalName = "Immutable"   -> compiler error

val valueWithType: String = "Explicit type"

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
    | SELECT *
    | FROM my_table
    | WHERE id = :id
    | -- can use " without escaping
""".trimMargin()
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
println("square(3) == ${square(3)}")

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

---

## Default values
 - Function arguments can have default value

```kotlin
fun printTemperature(degrees: Float, unit: String = "Celcius") {
    println("temperatur is $degrees $unit" )
}

printTemperature(37.0, "Celcius")
printTemperature(37.0)
```
todo: vis output

---

## Named arguments
 - Confusing with methods having many similar arguments
   - what are the true, true ?

```kotlin
fun confusing(name: String, active: Boolean, admin: Boolean){}

confusing("Ole", true, true)
confusing(name = "Ole", admin = true, active = false)
```
todo: må vi navngi alle?

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
- But 'var' isn't functional !

---

## Pattern to avoid var
 - Use `when` as expression
 - Must cover all possibilities

```kotlin
val surprise: Any = getSomething()
val whatIsIt: String = when ( surprise ){
    is String -> surprise.toUpperCase()
    42        -> "Life, the universe and everything"
    3.14      -> "PI"
    else      -> "Whatever"
}
```

// eksempel på when {}
//eksempel på assignment va inne i when
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



## Classes
 - Primary constructor
 - Default public
 - no `new` when instansiating
 - only `val` values available in methods

```kotlin
class Person(firstName: String, val lastName: String){
    //initialization as part of contruction
    val _first = firstName
    fun fullName() = "$_first $lastName".toUpperCase()
}

val person = Person("Bjørn", "Hamre")
println( person.fullName() ) //BJØRN HAMRE
```
---

## Access modifiers
Properties and methods of a class can be
- public (default): available to all
- private: only available from within the class
- protected: available iwthin the class, in sub-classes and classes in same package
- internal: avalable from any code in the same module (=copmiled together)

---

## Access getters and setters
- Access permission for getter is same as property
- Access permission for setter is same as property if not stated otherwise.
- public is default if nothing is specified

---

## Nice class features
 - Default values
 - Named arguments

```kotlin
data class Matrikkel(
        val kommunenr  : Int,
        val gaardsnr   : Int,
        val bruksnr    : Int,
        val festenr    : Int = 0,
        val seksjonsnr : Int = 0
)

val m1 = Matrikkel(1201, 1, 10, 0, 0)
val m2 = Matrikkel(1201, 1, 10)
val m3 = Matrikkel(kommunenr = 1201, gaardsnr = 1, 
    bruksnr = 10, seksjonsnr = 1)
```
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

## More about classes
 - Can be abstract, sealed, open
 - Can have several constructors
 - Can implement interfaces
 - supports inheritance
 - ...whatever java classes can do

---

## Data class (Scala case class)
 - Immutable, toString, equals, hashCode
   - without lombok !
 - .copy(), named arguments
 - testdata !

```kotlin
data class Employee(
        val name: String,
        val email: String,
        val salary: Long
)
val emp = Employee("Bjørn", "bha@ambita.com", 5_000_000)
println(emp) //Employee(name=Bjørn, email=bha@ambita.com, salary=5000000)
val empJunior = emp.copy(name = "Junior", salary = 500_000)
```
Note:
 - Can implement methods
 - unsure about inheritance


---

## Singletons/utility classes
 - no private constructor and hassle

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
object Util {
fun numberOfProcessors() = Runtime.getRuntime().availableProcessors()
}
The Util object we created using the object declaration is a singleton. We can’t
create objects of Util —it’s not considered to be a class by the Kotlin compiler;
it’s already an object. Think of it like a Java class with a private constructor
and only static methods

If a
group of functions are high level, general, and widely useful then place them
directly within a package. If on the other hand, a few functions are more
closely related to each other than the other functions, like f2c() and c2f() are
more closely related to each other than to milesToKm() ,then place them within
a singleton.

In Kotlin you access properties by providing the name of the property instead
of the getter or setter.


---

## Companion object
 - object inside class
 - E.g. factory
 - java: static methods

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

---

## Tuples
 - to
 - destructuring

```kotlin
val tuple = 42 to "The meaning" //vis type
println( "tuple: $tuple")
val theSecret = tuple.first
val (secret, message) = tuple
println("secret: $secret, message: $message")
```

---

## Data class is tuple
destructuring

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
