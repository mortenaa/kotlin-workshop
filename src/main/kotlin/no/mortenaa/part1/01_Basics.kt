package no.mortenaa.part1

/*
    Expressions. Functions. Types
    Kotlin type hierarchy
    unit, nothing
    Named arguments. Optional arguments (default verdi).
    Assignment. Val, Var
    Packages, files and classes. top level functions
    return statement. One line functions
    String formatting
    Enums and when
    Nullable
    Smart casts
    null safe operators
    Safe cast
    Quoted function name
    Lambdas
 */

fun helloWorld(): String {
    TODO("""
        Make this function return the String "Hello World!"
        """)
}

fun assignment(): Int {
    val a: Int = TODO("Assign a value to a such that the function returns 25")
    val b = 5
    return a * b
}

fun square(n: Int): Int = TODO("Make the function return the square of the input parameter 'n'")

fun max(n: Int, m: Int): Int {
    TODO("Make this function return the highest number of 'n' or 'm'")
}

fun