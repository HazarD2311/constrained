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
open class MinBigDecimalConstraint(val min: BigDecimal) : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal): Boolean =
            value.isGreaterOrEquals(min)
}

/**
 * Не больше, чем [max]
 */
open class MaxBigDecimalConstraint(val max: BigDecimal) : Constraint<BigDecimal> {

    override fun isValid(value: BigDecimal): Boolean =
            value.isLess(max)
}

/**
 * Число положительное
 */
class PositiveBigDecimalConstraint : MinBigDecimalConstraint(BigDecimal.ZERO)

/**
 * Число отрицательное
 */
class NegativeBigDecimalConstraint : MaxBigDecimalConstraint(BigDecimal.ZERO)
