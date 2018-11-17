package constrained

import constrained.base.BaseConstrained
import constrained.base.Constraint
import constrained.base.IConstrained

/**
 * Помечает поле [T],
 * как имеющее какие-либо ограничения.
 */
class Constrained<T>(
        private var value: T,
        constraints: MutableList<Constraint<T>> = mutableListOf()
) : IConstrained<T>, BaseConstrained<T>(constraints) {

    override val noError: Boolean
        get() = validate().isEmpty()

    /**
     * Проверить все ограничения
     * @return список ограничений, которое не прошло помеченное поле
     */
    override fun validate(): List<Constraint<T>> =
            constraints.filter { !it.isValid(value) }

    override fun setValueAnd(value: T) = this.apply { setValue(value) }

    override fun setValue(value: T) {
        this.value = value
    }

    override fun getValue(): T = value
}