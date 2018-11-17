package constrained.constraints.string

import constrained.base.Constraint

/**
 * Ограничение на длину
 * @param min минимальная длина
 * @param max максимальная длина
 *
 * * не самая удачная реализация
 * * лучше использовать отдельно [MinLengthConstraint] и [MaxLengthConstraint]
 */
class RangeConstraint(var min: Int, var max: Int) : Constraint<String> {

    var typeError: Type = Type.UNKNOWN
        private set

    init {
        if (min == max) min = 0 //спросите у сервера почему приходят одинаковые числа
    }

    private val minConstraint = MinLengthConstraint(min)
    private val maxConstraint = MaxLengthConstraint(max)

    override fun isValid(value: String): Boolean =
            checkMin(value) && checkMax(value)

    private fun checkMax(value: String): Boolean {
        val isValid = maxConstraint.isValid(value)
        return check(isValid, Type.MAX)
    }

    private fun checkMin(value: String): Boolean {
        val isValid = minConstraint.isValid(value)
        return check(isValid, Type.MIN)
    }

    private fun check(isValid: Boolean, type: Type): Boolean {
        return if (isValid) {
            true
        } else {
            typeError = type
            false
        }
    }

    enum class Type {
        MIN,
        MAX,
        UNKNOWN
    }
}