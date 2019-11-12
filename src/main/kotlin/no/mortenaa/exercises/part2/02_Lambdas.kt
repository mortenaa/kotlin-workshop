package no.mortenaa.exercises.part2


/**
 * 1. Filter with lambda
 *
 * [String.filter] takes as argument a lambda with the signature '(Char) -> Boolean'
 * Use this with a lambda to return the input String with all lower case characters removed.
 */
fun filterUpperCase(s: String): String {
    return s.filter { !it.isLowerCase() }
}

/**
 * 2. Count letters
 *
 * [String.count] can take a lambda with signature '(Char) -> Boolean' to return a count
 * of how many characters in a String for which the lambda returns 'true'
 * Use it to count the number of occurrences of the Char [c] in the String [s]
 */
fun letterCount(s: String, c: Char): Int {
    return s.count { it == c }
}

/**
 * 3. One or Zero
 *
 * Given an Int and a lambda, return 1 if the lambda is true for the int,
 * otherwise 0
 */
fun oneOrZero(int: Int, lambda: (Int) -> Boolean): Int {
    return if (lambda(int)) 1 else 0
}

/**
 * 4. Operation
 *
 * Given two Ints and a lambda, return the result of applying the lambda on
 * the two ints.
 */
fun intOperation(i1: Int, i2: Int, operation: (Int, Int) -> Int): Int {
    return operation(i1, i2)
}

/**
 * 5. Lambda as return value
 *
 * Given a prefix and a postfix String, return a lambda with signature (String) -> String
 * which given a string return a new string where the input is surrounded by prefix and postfix
 */
fun surroundString(prefix: String, postfix: String): (String) -> String {
    return { "$prefix$it$postfix" }
}