package no.mortenaa.exercises.part2

data class Task(val id: Long, val description: String, val priority: Int, val completed: Boolean? = false) : Comparable<Task> {
    override fun compareTo(other: Task): Int {
        return priority.compareTo(other.priority)
    }
}

/**
 * 1. Create an Instance.
 *
 * Return an instance of the [Task] class with id 100, description "Buy milk", and priority 1
 */
fun taskInstanse(): Task {
    return Task(id = 100, description = "Buy milk", priority = 1)
}

/**
 * 2. Add a property.
 *
 * Add a [Boolean] property called 'completed' to the Task class with a default value of 'false',
 * then return an instance of [Task] where 'completed' == 'true'
 *
 */
fun completedTask(): Task {
    return Task(101, "Take out the trash", priority = 10, completed = true)
}

/**
 * 3. Implement an interface.
 *
 * Implement the [Comparable] ('Comparable<Task>')interface for Task, so tasks can be ordered by priority
 * t1 > t2 should be true if t1.priority > t2.priority
 * Then return the result of calling t1.compareTo(2) from this function
 */
fun comparable(t1: Task, t2: Task): Int {
    return t1.compareTo(t2)
}

/**
 * 4. Equality
 *
 * Given two [Task] instances, return true if they are equal, that is if all properties
 * are equals. Does comparing the instances with '==' work?
 */
fun equality(t1: Task, t2: Task): Boolean {
    return with(t1) {
        id == t2.id && description == t2.description && priority == t2.priority && completed == t2.completed
    }
}

/**
 * 5. Data class
 *
 * Change Task to be a 'data class'
 * Now, see if you can find a simpler way to compare two instances for equality?
 *
 *
 */
fun dataClassEquality(t1: Task, t2: Task): Boolean {
    return t1 == t2
}

/**
 * 6. Copy
 *
 * Given an Instance of [Task] as input, return another instance that is equal to it,
 * but with 'completed' set to 'true'. Since [Task] now is a 'data class' you should
 * be able to do this in a single line
 */
fun copyAndComplete(task: Task): Task {
    return task.copy(completed = true)
}