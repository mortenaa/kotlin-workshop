package no.mortenaa.exercises.part1

import io.kotlintest.fail
import io.kotlintest.matchers.match
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import no.mortenaa.service.*
import java.time.Instant

class TypesTest : StringSpec({

    "1. nullabillity Test" {
        nullable("Lea", null, "Williamson", "footballer") shouldBe """
            FIRSTNAME: Lea
            MIDDLENAME: NA
            LASTNAME: Williamson
            OCCUPTION: Fotballer
        """.trimIndent()
    }

    "2. petStoreTest" {
        val petStoreService = mockk<PetStoreService>()
        val category = object : Category {
            override val id = 4242L
            override val name = "Dog"
        }
        val pet = object : Pet {
            override val id = 1010L
            override val name = "Rufus"
            override val category = category
            override val tags = null

        }
        every { petStoreService.findById(1010L) } returns pet
        findPetAndCategoryName(petStoreService, 1010L) shouldBe Pair("Rufus", "Dog")
    }

    "3. mystery input test" {
        mysteryInput("Arsenal") shouldBe 'A'
        mysteryInput(100) shouldBe 50
        mysteryInput(3) shouldBe 1
        mysteryInput(3.14) shouldBe 0
        mysteryInput(-1.0) shouldBe -1
        mysteryInput(Instant.now()) shouldBe null
    }

})