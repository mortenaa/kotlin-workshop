package no.mortenaa.exercises.part1

import no.mortenaa.service.petstore.Pet
import no.mortenaa.service.petstore.PetId
import no.mortenaa.service.petstore.PetStoreService


/**
 * 1. Nullabillity
 *
 * Return a multiline string with the parameter names and values. Parameter names
 * should be uppercase, values should be capitalized, and null values should be represented by "NA"
 *
 * example:
 *   FIRSTNAME: Lea
 *   MIDDLENAME: NA
 *   LASTNAME: Williamson
 *   OCCUPATION: Footballer
 *
 */
fun nullable(firstName: String, middleName: String?, lastName: String, occupation: String?): String {
    return """
        FIRSTNAME: ${firstName.capitalize()}
        MIDDLENAME: ${middleName?.capitalize() ?: "NA"}
        LASTNAME: ${lastName.capitalize()}
        OCCUPATION: ${occupation?.capitalize() ?: "NA"}
    """.trimIndent()
}

/**
 * 2. Nullabillity and Elvis
 *
 * Given the supplied petStoreService and petId, fix the commented out code (it won't compile as it is)
 *
 */
fun findPetAndCategoryName(petStoreService: PetStoreService, petId: PetId): Pair<String, String>? {
    val pet: Pet? = petStoreService.findById(petId)
    return Pair(pet?.name ?: "NA", pet?.category?.name ?: "NA")
}
//todo: hva skal returnerer i firste element? description not an element

/**
 * 3. When expression and Smart Cast
 *
 * This function should decide what type the input is, and return
 *  the first character if it is a String
 *  the bytes / 2 if it's an even Int
 *  1 if it's an odd Int
 *  0 if it's a Double greater or equal to 0
 *  -1 if it's a Double less than 0
 *  null for all other input
 *
 */
fun mysteryInput(input: Any?): Any? {
    return when {
        input is String -> input.first()
        input is Int && input.rem(2) == 0 -> input / 2
        input is Int -> 1
        input is Double && input >= 0 -> 0
        input is Double -> -1
        else -> null
    }
}