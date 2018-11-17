package constrained.base

/**
 * Интерфейс для сущности, которая имеет
 * некоторые ограничения
 */
interface IConstrained<T> {

    /**
     * Проверить все ограничения
     *
     * @return список ограничений, которое не прошло помеченное поле
     */
    fun validate(): List<Constraint<T>>

    fun setValueAndValidate(value: T): List<Constraint<T>>

    fun setValue(value: T)
    fun getValue(): T
}