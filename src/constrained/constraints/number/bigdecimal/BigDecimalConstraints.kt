package constrained.constraints.number.bigdecimal

import constrained.base.Constraint
import java.math.BigDecimal

/**
 * Не равно нулю
 */
class NonZeroBigDecimalConstraint : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal) =
            value.isEquals(BigDecimal.ZERO).not()
}

/**
 * Не меньше, чем [min]
 */
data class MinBigDecimalConstraint(val min: BigDecimal) : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal): Boolean =
            value.isGreaterOrEquals(min)
}

/**
 * Не больше, чем [max]
 */
data class MaxBigDecimalConstraint(val max: BigDecimal) : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal): Boolean =
            value.isLess(max)
}

/**
 * Число положительное
 */
class PositiveBigDecimalConstraint : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal): Boolean =
            MinBigDecimalConstraint(BigDecimal.ZERO).isValid(value)
}

/**
 * Число отрицательное
 */
class NegativeBigDecimalConstraint : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal): Boolean =
            MaxBigDecimalConstraint(BigDecimal.ZERO).isValid(value)
}
