package no.mortenaa.exercises.part1

/**
 *
 * Kotlin Workshop Exercises Part 1
 *
 */


/**
 * 1. Hello World.
 *
 * Make the function [helloWorld] return the [String] "Hello World!"
 */
fun helloWorld(): String {
    return "Hello World!"
}

/**
 * 2. Assignment.
 *
 * Assign a value to [a] such that the function returns 25"
 *
 */
fun assignment(): Int {
    val a: Int = 5
    val b = 5
    return a * b
}

/**
 * 3. Var and Val
 *
 * Rewrite the function to use [val] instead of [var]
 * (this exercise cannot be verified by the test code, so simply
 * remove the call to fail("...") from the corresponding test when you're done)
 */
fun varAndVal(a: String, b: String): String {
    val name: String = a.capitalize() + " and " + b.capitalize()
    return name
}

/**
 * 4. Square
 *
 * Return the square of the input [n]
 */
fun square(n: Int): Int = n * n

/**
 * 5. Max.
 *
 * Make this function return the highest number of [n] or [m]
 */
fun max(n: Int, m: Int): Int {
    return if (n > m) n else m
}

/**
 * 6. Max of 3.
 *
 * Make this function return the highest number of [n], [m] and [i]
 */
fun maxOf3(n: Int, m: Int, i: Int): Int {
    return max(max(n, m), i)
}

/**
 * 7. Absolute value
 *
 * Implement a function to return the absolute value of the input
 * (without using the built in [Double.absoluteValue])
 */
fun abs(value: Double): Double {
    return if (value < 0) -value else value
}

// EXTRA if you've got time in the end, come back and solve these

/**
 * 8. Leap Year
 *
 * Decide if the given year is a leap year.
 *
 * Every year that is exactly divisible by four is a leap year,
 * except for years that are exactly divisible by 100,
 * but these centurial years *are* leap years if they are exactly divisible by 400.
 *
 * The function [Int.rem] will be usefull
 *
 */
fun isLeapYear(year: Int): Boolean {
  return when {
    year.rem(400) == 0 -> true
    year.rem(100) == 0 -> false
    year.rem(4) == 0 -> true
    else -> false
  }
}


/**
 * 9. Pace caclculator
 *
 * Given a distance in meters, and the time it took to run it in minutes and seconds,
 * calculate the pace (as minutes and seconds per km)
 *
 * use `Pair(minutes, seconds)` to return two values
 *
 *
 */
fun pace(distance: Int, minutes: Int, seconds: Int): Pair<Int, Int> {
    val t = minutes * 60 + seconds
    val paceSeconds = t * 1000 / distance
    return paceSeconds.div(60) to paceSeconds.rem(60)
}
