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
fun helloWorld(): String = "Hello World!"

/**
 * 2. Assignment.
 *
 * Assign a bytes to [a] such that the function returns 25"
 *
 */
fun assignment(): Int {
    val a = 5
    val b = 5
    return a * b
}

/**
 * 3. Var and Val
 *
 * Rewrite the function to use [val] instead of [var]
 * (this exercise cannot be verified by the test code, so simply
 * remove the call to fail("...") when you're done)
 *
 */
fun varAndVal(a: String, b: String): String {
    val name = "${a.capitalize()} and ${b.capitalize()}"
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
fun max(n: Int, m: Int): Int = if (n > m) n else m

/**
 * 6. Max of 3.
 *
 * Make this function return the highest number of [n], [m] and [i]
 */
fun maxOf3(n: Int, m: Int, i: Int): Int = max(n, max(m, i))

/**
 * 7. Absolute bytes
 *
 * Implement a function to return the absolute bytes of the input
 * (without using the built in [Double.absoluteValue])
 */
fun abs(value: Double): Double = if (value >= 0) value else -value

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
        year.rem(4) != 0 -> false
        year.rem(400) == 0 -> true
        year.rem(100) == 0 -> false
        else -> true
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
    val km = distance / 1000.0
    val minPerKm: Int = (minutes / km).toInt()
    val secLefts = ( (minutes/km) - minPerKm ) * 60
    val secPerKm = seconds/km
    return Pair(minPerKm, secLefts.toInt() + secPerKm.toInt() )
}
