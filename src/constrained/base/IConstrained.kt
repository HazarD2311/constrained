package constrained.base

/**
 * Интерфейс для сущности, которая имеет
 * некоторые ограничения
 */
interface IConstrained<T> {

    /**
     * Валидно ли поле [T] в данный момент
     */
    val noError: Boolean

    /**
     * Проверить все ограничения
     *
     * @return список ограничений, которое не прошло помеченное поле
     */
    fun validate(): List<Constraint<T>>

    fun setValue(value: T)
    fun setValueAnd(value: T): IConstrained<T>
    fun getValue(): T
}