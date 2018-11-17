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

    /**
     * Проверить все ограничения
     * @return список ограничений, которое не прошло помеченное поле
     */
    override fun validate(): List<Constraint<T>> =
            constraints.filter { !it.isValid(value) }

    override fun setValueAndValidate(value: T): List<Constraint<T>> {
        setValue(value)
        return validate()
    }

    override fun setValue(value: T) {
        this.value = value
    }

    override fun getValue(): T = value
}