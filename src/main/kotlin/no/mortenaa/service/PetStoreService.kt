package no.mortenaa.service

typealias PetId = Long
typealias CategoryId = Long
typealias TagId = Long

enum class Status { AVAILABLE, PENDING, SOLD }

interface PetStoreService {
    fun findById(petId: PetId): Pet?
}

interface Pet {
    val id: PetId
    val name: String
    val category: Category?
    val tags: Set<Tag>?
}

interface Category {
    val id: CategoryId
    val name: String
}

interface Tag {
    val id: TagId
    val name: String
}