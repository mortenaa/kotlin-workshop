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

class ClassesTest : StringSpec({

    "1. An instance of Task should be returned" {
        val task = taskInstanse()
        task should beOfType<Task>()
        task.description shouldBe "Buy milk"
        task.priority shouldBe 1
        task.id shouldBe 100
    }

    "2. A 'completed' property should be added to Task" {
        val task = completedTask()

        // Uses reflection to check property so it will compile even before the exercise is done
        val completed: KProperty1<Task, Boolean>? = Task::class.declaredMemberProperties
            .find { it.name == "completed" } as KProperty1<Task, Boolean>?
        val constructor = Task::class.primaryConstructor
        constructor shouldNot beNull()
        withClue("Task should have a constructor taking 4 arguments:") { constructor!!.parameters shouldHaveSize(4) }
        withClue("The last constructor argument should be optional:") { constructor?.parameters?.get(3)?.isOptional shouldBe true }
        withClue("Task should have a property named 'completed':") { completed shouldNot beNull() }
        withClue("The property 'completed' should be 'true':") { completed?.invoke(task) shouldBe true }
    }

    "3. Task should implement the 'Comparable' interface" {
        val t1 = Task(1, "", 10)
        val t2 = Task(2, "", 11)

        comparable(t1, t2) shouldBe -1
        comparable(t1, t1) shouldBe 0
        comparable(t2, t1) shouldBe 1

        withClue("Task should implement the 'Comparable' interface") {
            Task::class.supertypes.first().classifier shouldBe Comparable::class
        }
        val compareTo = Task::class.memberFunctions.find { it.name == "compareTo" }
        compareTo?.call(t1, t2) shouldBe -1
    }

})