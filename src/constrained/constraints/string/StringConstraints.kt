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
class MinLengthConstraint(val min: Int) : Constraint<String> {

    override fun isValid(value: String) =
            value.length >= min
}

/**
 * Ограничение на [max] длину
 */
class MaxLengthConstraint(val max: Int) : Constraint<String> {

    override fun isValid(value: String): Boolean =
            value.length <= max
}

/**
 * Ограничение строки на паттерн
 * @param regexPattern который должен удовлетворять
 */
class PatternConstraint(regexPattern: String) : Constraint<String> {

    private val pattern: Regex = regexPattern.toRegex()

    override fun isValid(value: String) =
            pattern.matches(value)
}

/**
 * Не может быть "null"
 * * обычно для валидации значение с сервера
 */
class NonNullConstraint : Constraint<String> {

    override fun isValid(value: String) =
            value != "null"
}