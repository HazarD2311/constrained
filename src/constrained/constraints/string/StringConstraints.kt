package constrained.constraints.string

import constrained.base.Constraint

/**
 * Набор основных [Constraint] для строковых полей,
 * покрывающее основные возможные варианты
 * * обычно использовать для полей ввода
 */

/**
 * Значение не может быть пустым
 */
class NonEmptyConstraint : Constraint<String> {

    override fun isValid(value: String): Boolean =
            value.isNotBlank()
}

/**
 * Ограничение на [min] длину
 */
data class MinLengthConstraint(val min: Int) : Constraint<String> {

    override fun isValid(value: String) =
            value.length >= min
}

/**
 * Ограничение на [max] длину
 */
data class MaxLengthConstraint(val max: Int) : Constraint<String> {

    override fun isValid(value: String): Boolean =
            value.length <= max
}

/**
 * Ограничение строки на паттерн
 * @param regexPattern который должен удовлетворять
 */
data class PatternConstraint(val regexPattern: String) : Constraint<String> {

    private val pattern: Regex = regexPattern.toRegex()

    override fun isValid(value: String) =
            pattern.matches(value)
}