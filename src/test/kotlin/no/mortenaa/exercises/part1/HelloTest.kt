package no.mortenaa.exercises.part1

import io.kotlintest.matchers.string.shouldStartWith
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class AcronymTest : StringSpec({

    "the acronym of an empty string should be an empty string" {
        acronym("") shouldBe ""
    }


    "test some common acronyms" {
        acronym("GNU's not Unix!") shouldBe "GNU"
        acronym("")
    }

    "startsWith should test for a prefix" {
        "world" shouldStartWith "wor"
    }

})
