package no.mortenaa.exercises.part1

import io.kotlintest.fail
import io.kotlintest.matchers.match
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class StringsTest : StringSpec({

    "1. stringFormat Test" {
        stringFormat("Answer", 42) shouldBe "Answer=42"
    }

    "2. splitString Test" {
        splitString("little, kim, 28") shouldBe "KL: 28"
    }

    "3. palindrome Test" {
        isPalindrome("palindrome") shouldBe false
        isPalindrome("racecar") shouldBe true
    }

    "4. jsonFormat should format the input as a json list" {
        jsonFormat(listOf(1, 2, 3, 4)) shouldBe "[1, 2, 3, 4]"
        jsonFormat(emptyList()) shouldBe "[]"
        jsonFormat(listOf(1)) shouldBe "[1]"
    }


    "5. movieToJson Test" {
        val strippedJson = movieToJson("Avengers: Endgame", 2019, 181).filter { !it.isWhitespace() }
        strippedJson shouldBe """{"title":"Avengers:Endgame","year":2019,"runtime":181,"rating":"PG-12","country":"USA"}"""
    }

    "6. acronym Test" {
        acronym("application programming interface") shouldBe "API"
        acronym("gnu's not unix") shouldBe "GNU"
    }



})