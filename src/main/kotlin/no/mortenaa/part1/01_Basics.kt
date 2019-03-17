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

/**
 * Hello World.
 *
 * Make the function [helloWorld] return the [String] "Hello Word!"
 */
fun helloWorld(): String {
    TODO()
}

/**
 * Assignment.
 *
 * Assign a value to [a] such that the function returns 25"
 */
fun assignment(): Int {
    val a: Int = TODO()
    val b = 5
    return a * b
}

/**
 * Square
 *
 * Return the square of the input [n]
 */
fun square(n: Int): Int = TODO()

/**
 * Max.
 *
 * Make this function return the highest number of [n] or [m]
 */
fun max(n: Int, m: Int): Int {
    TODO()
}

/**
 * StringFormat.
 *
 * Use string templates ($) to format [name] and [value] as "name=value"
 */
fun stringFormat(name: String, value: Number): String {
    TODO()
}

/**
 * Json Format
 *
 * Use the function [joinToString] on the input [list] to return the list in a csv format.
 * i.e. 1, 2, 3 => "[1, 2, 3]"
 *
 * HINT: Use the named arguments `prefix` and `postfix`
 */
fun jsonFormat(list: List<Int>): String {
    TODO()
}