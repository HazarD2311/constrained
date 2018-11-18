package constrained.constraints.number

import constrained.base.Constraint

/**
 * Не равно нулю
 */
class NonZeroConstraint : Constraint<Number> {

    override fun isValid(value: Number) =
            value.toInt() != 0
}

/**
 * Не меньше, чем [min]
 */
data class MinConstraint(val min: Number) : Constraint<Number> {

    override fun isValid(value: Number): Boolean =
            value.toInt() >= min.toInt()
}

/**
 * Не больше, чем [max]
 */
data class MaxConstraint(val max: Number) : Constraint<Number> {

    override fun isValid(value: Number): Boolean =
            value.toInt() < max.toInt()
}

/**
 * Число положительное
 */
class PositiveNumberConstraint : Constraint<Number> {

    override fun isValid(value: Number): Boolean =
            MinConstraint(0).isValid(value)
}

/**
 * Число отрицательное
 */
class NegativeNumberConstraint : Constraint<Number> {

    override fun isValid(value: Number): Boolean =
            MaxConstraint(0).isValid(value)
}