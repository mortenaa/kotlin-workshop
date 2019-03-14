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
 - Maven plugin
 - easy to run scripts (.kts)

---

## Datatypes in Kotlin
"Everything is an object"
 - Double, Float
 - Long, Int
 - Short, Byte
 - String, Char
 - ~~Long[]~~, `Array<Long>`
 - Any, All, Unit

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

## Default values
 - Function arguments can have default value

```kotlin
fun printTemperature(degrees: Float, unit: String = "Celcius") {
    println("temperatur is $degrees $unit" )
}

printTemperature(37.0, "Celcius")
printTemperature(37.0)
```

---

## Named arguments
 - Confusing with methods having many similar arguments
   - what are the true, true ?

```kotlin
fun confusing(name: String, active: Boolean, admin: Boolean){}

confusing("Ole", true, true)
confusing(name = "Ole", admin = true, active = false)
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

## Class modifiers



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
