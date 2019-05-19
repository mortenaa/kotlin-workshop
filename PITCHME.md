# Kotlin workshop
## Morten Nygaard Åsnes 
## Bjørn Hamre

---

# Program

 - Part 1 - Syntax / Functions / Types
    - 40 min. introduction
    - 40 min. exercises
    - 10 min. solutions
 - Part 2 - Classes / Objects / Collection
    - 30 min. theory
    - 40 min. exercises
    - 10 min. solutions 
---

## About Kotlin

 - New programming language
 - Developed by Jetbrains
 - Inspired (a lot) by Scala, Groovy and others
 
--- 
 
## Resources
 - https://kotlinlang.org/docs/reference/
 - https://play.kotlinlang.org/koans/overview
 - https://www.coursera.org/learn/kotlin-for-java-developers

---

## Why Kotlin
 - Less boilerplate -> Less code -> Fewer bugs
 - More declarative code
 - More functional style
 - Better support for immutability
 - Null safe

---
Note:
Popular on android. Supported by Google.

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
Note:
Not primitive types. Basic types are compiled to native Jvm types where possible.

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

Note:
Semicolons are optional, but are by convention only used to separate multiple statements on the same line.

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
Note:
$ is used for single variables, ${} evaluating expressions

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
 - a top level function will be compiled in a class corresponding to the source file on the jvm.
---

## No return value
 - Will actually return an object of type Unit
 - Unit can be inferred

```kotlin
fun printer(n: Int) {
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
if (middleName != null) {
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
if (something is String) {
    println(something.toUpperCase())
}

```

---

## Pattern matching
 - Notice the smart casting (is)
 
```kotlin
val surprise: Any = getSomething()
var whatIsIt: String? = null
when (surprise) {
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
val whatIsIt: String = when (surprise) {
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
val absolute = if (random < 0) -random else random
```

---

## Pattern to avoid var
 - try catch as an expression
 - Implicit return

```kotlin
val value = try {
    getSomething() as String
} catch (e: Exception) {
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
 - No `new` when instantiating
 - Getters and Setters (for vars) generated

```kotlin
class Person(val firstName: String, var lastName: String) {
}

val bjorn = Person("Bjørn", "Hamre")
println(bjorn.firstName)
bjorn.lastName = "Something Else"
```
Note:
- From Kotlin code, uses property name
   - In byte code -> generated as get set
   - Boilerplate removed for ease of reading
- From java use getter and setter

---

## Class Person in byte code
```
$ javap Classes\$Person.class
Compiled from "classes.kts"
public final class no.hamre.Classes$Person {
  public final java.lang.String getFirstName();
  public final java.lang.String getLastName();
  public final void setLastName(java.lang.String);
  public no.hamre.Classes$Person(java.lang.String, java.lang.String);
}

```

---

## Nice class features
 - Default values
 - Named arguments

```kotlin
class Person(val lastName: String,
             val firstName: String,
             val middleName: String = "") {
    fun fullName() = "$firstName $middleName $lastName"
}

val defaultMiddleName = Person("Hamre", "Bjørn" )
val namedArguments = Person(
    firstName = "Bjørn",
    middleName = "Håkonsen",
    lastName = "Hamre")

println(defaultMiddleName.fullName()) //> Bjørn Hamre
println(namedArguments.fullName())    //> Bjørn Håkonsen Hamre
```
Note:
- Put default values at the end of the signature
- When using named arguments to the constructor it looks a lot like a builder

---
## Inheritance
 - Superclass must be open
 - Using ':'
 - Must call constructor on superclass

```kotlin
open class Person(val name: String)
class IdentifiablePerson(val ssn: String, name: String) : Person(name)

val citizen = IdentifiablePerson("01017012345", "Bjørn")
println("Name: ${citizen.name}, ssn: ${citizen.ssn}")
> Name: Bjørn, ssn: 01017012345
```
Note:
 - Classes are closed by default
 - Eksempel til senere hvis vi skal vise get() og fields
open class Person(val name: String)
class IdentifiablePerson(_ssn: String, name: String) : Person(name){
    val ssn: String = _ssn
        get() = "${field.substring(0, 6)}*****"
}

val citizen = IdentifiablePerson("01017012345", "Bjørn")
println("Name: ${citizen.name}, ssn: ${citizen.ssn}")

---

## Implement interface
 - Using ':' as inheritance
 - Must have override on methods

```kotlin
interface PersonService {
    fun addPerson(personToAdd: Person)
}

class PersonServiceImpl : PersonService {
    override fun addPerson(personToAdd: Person) {
        println("Persisting to database: $personToAdd")
    }
}
```

Note:
Skal vi si noe om sealed?
Sealed classes must have all subclasses implemented in the same file.
All possible implementations of a seal class are known
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
 - Inheritance: A data class can extend but not be extended
 - Copy with named parameters is usefull for making a copy with a few parameters changed.
   Usefull for keeping objects immutable. Make a modified copy instead of modifying. 
---

## Enums 

```kotlin
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//Can have properties
enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
}
Color.RED.rgb
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
Note:
//todo: .toMutable
//ikke immutable i bytekode/java
---

## Accessing elements
- Accessed by `[index]` or `.get(index)`
- `+` appends an element (last/to the right)
- Can be copied to mutable

```kotlin
val fruits = listOf("Apple", "Banana")
val apple = fruits[0]
val banana = fruits.get(1)

val moreFruits = fruits + "Orange"
val mixedUp: String = "Orange" + fruits
val mutableFruits = moreFruits.toMutableList()
mutableFruits.add("Kiwi")
```

---

## Filter and Map collections
 - Like in Java
 - No .stream() or .collect()
 - Implicit parameter is named 'it'

```kotlin
data class Employee(val name: String, val salary: Long)

val employees = listOf(
    Employee("Bjørn", 1_000_000),
    Employee("Junior", 300_000),
    Employee("The Boss", 5_000_000)
)

val highSalaries: List<Long> =
    employees.filter { emp -> emp.salary > 500_000 }
        .filter { it.salary > 500_000 }
        .map { it.salary }
val average = highSalaries.average()

```

---

## Reduce a collection
- reduce/reduceRight
   - Starts with first two elements
- fold/foldRight
   - Requires initial value
   - Starts with initial value and first element
   - Can be applied to empty collections

```kotlin
val sumReducedRight = employees.map { it.salary }
    .reduceRight(Long::plus)
val sumReduced = employees.map { it.salary }
    .reduce{ it, sum -> sum + it}

val sumFoldedRight = employees.map { it.salary }
    .foldRight(0L, Long::plus)
val sumFolded = employees.map { it.salary }
    .foldRight(0L){ it, sum -> it + sum }
```

---

## Lambdas
- Function style: `(params) -> returnType` 
- Types can be inferred in one position

```kotlin
val concatenator: (s1: String, s2: String) -> String 
    = { s1: String, s2: String -> s1+s2}
val concatenator: (s1: String, s2: String) -> String 
    = { s1, s2 -> s1+s2}
val concatenator = { s1: String, s2: String -> s1+s2}

val name = concatenator("First", "Last")
```

---

## Pass lambda as argument to function
- Lambda as last parameter
- Use separate block when calling

```kotlin
fun intOperator(v1: Int, v2: Int, operation: (Int, Int) -> Int ): Int = operation(v1, v2)
val sum = intOperator(2, 3) { n1, n2 -> n1 + n2 }
val sum2 = intOperator(2, 3, Int::plus)
```
---

## Return lambda from function
- Return type is a lambda

```kotlin
fun times(base: Int): (Int) -> Int = { value -> base * value }
val doubler = times(2)
val trippler: (Int) -> Int = times(3)
println("2 x 4 = ${doubler(4)}")
> 2 x 4 = 8
```
---

## Tuples/Pair
 - `to`
 - destructuring

```kotlin
val tuple = 42 to "The meaning"
val theSecret = tuple.first

val (secret, message) = tuple
```
---

## Data class is tuple
- Can be destructured
- `_` for unused values
- Order is important

```kotlin
data class Person(val name: String, val age: Int, val occupation: String)
val bjorn = Person("Bjørn", 46, "Programmer")
val (na, _, occ) = bjorn
```

---

# Part 3


---
## More about classes
 - Can be abstract, sealed, open
 - Can have several constructors
 - Can implement interfaces
 - supports inheritance
 - ...whatever java classes can do


---

## Singletons (utility classes)
- No private constructor and hassle

```kotlin
object Utilities{
    fun toUpper(text: String) = text.toUpperCase()
}

val upperCase = Utilities.toUpper("Bjørn")
```
note:
- Utililites object we created using the object declaration is a singleton. 
- We can’t create objects of Utilities — it’s not considered to be a class
   - it’s already an object.
- Think of it like a class with a private constructor and only static methods
   - but with less code.
- "static" utility classes
- Not static in the byte code
---

## Companion object
 - object inside class
 - Eg. factory

```kotlin
class MyClass private constructor() {
    companion object {
        fun create(): MyClass = MyClass()
    }
}
val instance = MyClass.create()
```

Note:
 - methods in companion object accessed with classname in Kotlin
 - private constructor

 - If a group of functions are high level, general, and widely useful then place them
directly within a package. 
- If on the other hand, a few functions are more closely related to each other than the other functions, like create() and configure(), are more closely related to each other than to milesToKm() ,then place them within a singleton.

---




