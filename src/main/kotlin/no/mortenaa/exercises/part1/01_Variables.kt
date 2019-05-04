package no.mortenaa.exercises.part1

import java.time.Instant
import java.time.LocalDate

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
    TODO()
}

/**
 * 2. Assignment.
 *
 * Assign a value to [a] such that the function returns 25"
 *
 */
fun assignment(): Int {
    val a: Int = TODO()
    val b = 5
    return a * b
}

/**
 * 3. Var and Val
 *
 * Rewrite the function to use [val] instead of [var]
 *
 */
fun varAndVal(a: String, b: String): String {
    var name: String = a.capitalize()
    name += " and "
    name += b.capitalize()
    return name
}

/**
 * 4. Strings
 *
 * Take the input in the form "Lastname, Firstname, age" and return
 * the a String containing "FL: Age"
 * where F and L are upcase initials
 *
 * [Char.toUpperCase]
 * [String.first]
 * [String.split]
 *
 */
fun strings(input: String): String {
    TODO()
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


/**
 * Anagram
 *
 * [String.reversed]
 *
 */
fun isAnagram(s: String): Boolean {
    TODO()
}

/**
 * Pangram
 */
fun isPangram(s: String): Boolean {
    TODO()
}

/**
 * Leap Year
 *
 * [Int.rem]
 *
 */
fun isLeapYear(year: Int): Boolean {
  TODO()
}

/**
 * Rot 13
 *
 */
fun rot13(s: String): String {
    TODO()
}

/**
 * Multiline String
 *
 * Format as JSON using multiline String and String interpolation
 * """
 * {
 *      "title": "Captain Marvel",
 *      ...
 * }
 * """
 */
fun movieToJson(title: String, productionYear: Int, runtime: Int, rating: String = "PG-12", country: String = "USA") {
    TODO()
}