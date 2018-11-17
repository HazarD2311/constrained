package constrained.base

/**
 * Базовый класс для полей, имеющие ограничения
 * Инкапсулирует в себе список [Constraint]
 */
abstract class BaseConstrained<T>(
        val constraints: MutableList<Constraint<T>> = mutableListOf()
) {

    fun add(constraint: Constraint<T>) {
        constraints.add(constraint)
    }

    fun addAll(constraints: List<Constraint<T>>) {
        this.constraints.addAll(constraints)
    }

    fun add(vararg constraints: Constraint<T>) {
        this.constraints.addAll(constraints)
    }
}