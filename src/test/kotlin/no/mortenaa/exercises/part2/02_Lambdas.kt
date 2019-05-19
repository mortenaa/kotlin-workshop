package no.mortenaa.exercises.part2

import io.kotlintest.matchers.beLessThan
import io.kotlintest.matchers.beOfType
import io.kotlintest.matchers.collections.contain
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.types.beNull
import io.kotlintest.matchers.withClue
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldNot
import io.kotlintest.specs.StringSpec
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.primaryConstructor

class LambdaTest : StringSpec({

    "1. Filter" {
        filterUpperCase("") shouldBe ""
        filterUpperCase("FooBar") shouldBe "FB"
        filterUpperCase("Foo Bar") shouldBe "F B"
        filterUpperCase("FOO BAR") shouldBe "FOO BAR"
        filterUpperCase("abcde") shouldBe ""
    }

    "2. Count Letters" {
        letterCount("", 'a') shouldBe 0
        letterCount("abc", 'b') shouldBe 1
        letterCount("xyzzy", 'z') shouldBe 2
    }

    "3. One or Zero" {
        oneOrZero(10) { true } shouldBe 1
        oneOrZero(10) { false } shouldBe 0
        oneOrZero(5) { it.rem(2) == 1 } shouldBe 1
        oneOrZero(5) { it.rem(2) == 0 } shouldBe 0
        oneOrZero(4) { it.rem(2) == 0 } shouldBe 1
        oneOrZero(4) { it.rem(2) == 1 } shouldBe 0
    }

    "4. Operation" {
        intOperation(4, 2, Int::plus) shouldBe 6
        intOperation(10, 5, Int::div) shouldBe 2
    }

    "5. Return Lambda" {
        surroundString("[", "]")("foo") shouldBe "[foo]"
        surroundString("", "")("bar") shouldBe "bar"
        surroundString("<", ">")("") shouldBe "<>"
    }

})