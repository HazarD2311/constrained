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
open class MinConstraint(val min: Number) : Constraint<Number> {

    override fun isValid(value: Number): Boolean =
            value.toInt() >= min.toInt()
}

/**
 * Не больше, чем [max]
 */
open class MaxConstraint(val max: Number) : Constraint<Number> {

    override fun isValid(value: Number): Boolean =
            value.toInt() < max.toInt()
}

/**
 * Число положительное
 */
class PositiveNumberConstraint : MinConstraint(0)

/**
 * Число отрицательное
 */
class NegativeNumberConstraint : MaxConstraint(0)