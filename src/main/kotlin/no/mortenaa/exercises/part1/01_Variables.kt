package no.mortenaa.exercises.part1

/**
 *
 * Variables
 *
 * - variable assignment
 * - difference of var and val
 * - add explicit type signature (how to test?)
 * - remove all type signatures while still compiles (how to test?)
 *
 */

/**
 * 1. Hello World.
 *
 * Make the function [helloWorld] return the [String] "Hello Word!"
 */
fun helloWorld(): String {
    return "Hello World!"
}

/**
 * 2. Assignment.
 *
 * Assign a value to [a] such that the function returns 25"
 */
fun assignment(): Int {
    val a: Int = 5
    val b = 5
    return a * b
}

/**
 * Square
 *
 * Return the square of the input [n]
 */
fun square(n: Int): Int = n * n

/**
 * Max.
 *
 * Make this function return the highest number of [n] or [m]
 */
fun max(n: Int, m: Int): Int {
    return if (n > m) n else m
}

/**
 * StringFormat.
 *
 * Use string templates ($) to format [name] and [value] as "name=value"
 */
fun stringFormat(name: String, value: Number): String {
    return "$name=$value"
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
    return list.joinToString(prefix = "[", postfix = "]")
}


fun sumSquared(x: Int, y: Int) = (x + y) * (x + y)



//TODO: lage egne funksjoner
//TODO: sealed -> option
