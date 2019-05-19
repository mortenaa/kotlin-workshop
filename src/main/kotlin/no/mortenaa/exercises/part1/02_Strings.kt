package no.mortenaa.exercises.part1

/**
 * 1. StringFormat.
 *
 * Use string templates ($) to format [name] and [value] as "description=bytes" //todo: villedende beskrivelse
 */
fun stringFormat(name: String, value: Number) = "$name=$value"

/**
 * 2. Split string
 *
 * Take the input in the form "Lastname, Firstname, age" and return
 * the a string formatted as "FL: Age"
 * where F and L are uppercase initials
 *
 * Useful functions:
 *   [Char.toUpperCase]
 *   [String.first]
 *   [String.split]
 *
 */
fun splitString(input: String): String {
    val parts = input.split(", ")
    return "${parts[1].first().toUpperCase()}${parts[0].first().toUpperCase()}: ${parts[2]}"
}

/**
 * 3. Palindrome
 *
 * Decide if the given string is a palindrome (reads the same forward and backwards).
 *
 * [String.reversed] can be useful
 *
 */
fun isPalindrome(s: String) = s == s.reversed()

/**
 * 4. Json Format
 *
 * Use the function [joinToString] on the input [list] to return the comma separated list inside brackets.
 * i.e. 1, 2, 3 => "[1, 2, 3]"
 *
 * HINT: Use the named arguments `prefix` and `postfix`
 */
fun jsonFormat(list: List<Int>) = list.joinToString( ", ",  "[", "]")

/**
 * 5. Multiline String
 *
 * Format as JSON using multiline String and String interpolation
 * """
 * {
 *      "title": "Captain Marvel",
 *      "year": 2019,
 *      ...
 * }
 * """
 */
fun movieToJson(title: String, year: Int, runtime: Int, rating: String = "PG-12", country: String = "USA") = """
    {
        "title": "$title",
        "year": $year,
        "runtime": $runtime,
        "rating": "$rating",
        "country": "$country"
    }
""".trimIndent()

/**
 * 6. Acronym
 *
 * Return the Acronym of the supplied title.
 *
 * Example: "application programming interface" => "API"
 *
 */
fun acronym(title: String) = title.split(" ").map{ it.first().toUpperCase() }.joinToString("")