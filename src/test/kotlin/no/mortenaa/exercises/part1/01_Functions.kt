package no.mortenaa.exercises.part1

import io.kotlintest.fail
import io.kotlintest.matchers.match
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class FunctionsTest : StringSpec({

    "1. the function 'helloWorld' should return 'Hello World!'" {
        helloWorld() should match("Hello World!")
    }

    "2. assignment() should return 25" {
        assignment() shouldBe 25
    }

    "3. use val instead of var" {
        fail("Remove this line when you have finished the exercise")
        varAndVal("Korg", "Miek") should match("Korg and Miek")
    }

    "4. square(n) should return n*n" {
        square(2) shouldBe 4
        square(5) shouldBe 25
        square(10) shouldBe 100
    }

    "5. max(n, m) should return the highest of n and m" {
        max(1, 2) shouldBe 2
        max(2, 1) shouldBe 2
        max(3, 3) shouldBe 3
        max(-1, -2) shouldBe -1
    }

    "6. maxOf3(n, m, i) should return the highest of n, m and i" {
        maxOf3(4, 1, 2) shouldBe 4
        maxOf3(-1, 3, 1) shouldBe 3
        maxOf3(3, 3, 3) shouldBe 3
        maxOf3(-1, -2, 0) shouldBe 0
    }

    "7. abs should return the absolute value" {
        abs(10.2) shouldBe 10.2
        abs(-5.0) shouldBe 5.0
    }

    "8. isLeapYear" {
        isLeapYear(2019) shouldBe false
        isLeapYear(2020) shouldBe true
        isLeapYear(1700) shouldBe false
        isLeapYear(1600) shouldBe true
        isLeapYear(1972) shouldBe true
        isLeapYear(1900) shouldBe false
    }

    "9. paceCalculator" {
        pace(1000, 60, 0) shouldBe Pair(6, 0)
        pace(5000, 25, 0) shouldBe Pair(5, 0)
        pace(3000, 20, 30) shouldBe Pair(6, 50)
    }


})