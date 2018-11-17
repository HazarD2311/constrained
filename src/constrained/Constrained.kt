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
        vararg constraints: Constraint<T>
) : IConstrained<T>, BaseConstrained<T>() {

    init {
        add(*constraints)
    }

    override val noError: Boolean
        get() = validate().isEmpty()

    /**
     * Проверить все ограничения
     * @return список ограничений, которое не прошло помеченное поле
     */
    override fun validate(): List<Constraint<T>> =
            constraints.filter { !it.isValid(value) }

    override fun setValueAnd(value: T): Constrained<T> = this.apply { setValue(value) }

    override fun setValue(value: T) {
        this.value = value
    }

    override fun getValue(): T = value
}