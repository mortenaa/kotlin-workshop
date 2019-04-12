package no.mortenaa.exercises.part2

import io.kotlintest.specs.StringSpec

class OptionTest : StringSpec ({

    "instance test" {
        Option.some("test") is Some
        Option.none() is None
        Option.none() === None
    }



})
