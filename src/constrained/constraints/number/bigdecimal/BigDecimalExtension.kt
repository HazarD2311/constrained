package constrained.constraints.number.bigdecimal

import java.math.BigDecimal

/**
 * Extension функции для работы с BigDecimal
 */

fun BigDecimal.isLess(second: BigDecimal?): Boolean {
    second ?: return false
    return this.compareTo(second) < 0 // "<"
}

fun BigDecimal.isGreater(second: BigDecimal?): Boolean {
    second ?: return false
    return this.compareTo(second) > 0 // ">"
}

fun BigDecimal.isLessOrEquals(second: BigDecimal?): Boolean {
    second ?: return false
    return this.compareTo(second) <= 0 // "<="
}

fun BigDecimal.isGreaterOrEquals(second: BigDecimal?): Boolean {
    second ?: return false
    return this.compareTo(second) >= 0 // ">="
}

fun BigDecimal.isEquals(second: BigDecimal?): Boolean {
    second ?: return false
    return this.compareTo(second) == 0 // "=="
}