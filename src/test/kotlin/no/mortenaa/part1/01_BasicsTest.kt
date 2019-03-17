package no.mortenaa.part1

import io.kotlintest.matchers.match
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BasicsTest : StringSpec({

    "the function 'helloWorld' should return 'Hello World!'" {
        helloWorld() should match("Hello World!")
    }

    "assignment() should return 25" {
        assignment() shouldBe 25
    }

})