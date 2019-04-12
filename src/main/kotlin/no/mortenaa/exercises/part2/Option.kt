package no.mortenaa.exercises.part2

import kotlin.Nothing

sealed class Option<T> {

    companion object {

        fun <T> some(t: T) = Some(t)

        fun none() = None

    }

}

data class Some<T>(val t: T) : Option<T>() {

}

object None : Option<Nothing>() {

}