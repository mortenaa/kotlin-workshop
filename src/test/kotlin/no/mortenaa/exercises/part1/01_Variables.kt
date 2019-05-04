package no.mortenaa.exercises.part1

import io.kotlintest.fail
import io.kotlintest.matchers.match
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BasicsTest : StringSpec({

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

    "3. square(n) should return n*n" {
        square(2) shouldBe 4
        square(5) shouldBe 25
        square(10) shouldBe 100
    }

    "4. max(n, m) should return the highest of n and m" {
        max(1, 2) shouldBe 2
        max(2, 1) shouldBe 2
        max(3, 3) shouldBe 3
        max(-1, -2) shouldBe -1
    }

    "5. should format input as name=value" {
        stringFormat("answer", 42) shouldBe "answer=42"
    }

    "6. jsonFormat should format the input as a json list" {
        jsonFormat(listOf(1, 2, 3, 4)) shouldBe "[1, 2, 3, 4]"
        jsonFormat(emptyList()) shouldBe "[]"
        jsonFormat(listOf(1)) shouldBe "[1]"
    }

})