package constrained

import constrained.base.Constraint
import constrained.constraints.string.NonEmptyConstraint

fun stringConstrained(vararg constraints: Constraint<String>) = Constrained("", NonEmptyConstraint())
        .apply { add(*constraints) }

/**
 * Если в списке есть тип [T],
 * то применить лямду [letAction] к найденному элементу
 * @return [R] - то, что вернет [letAction]
 */
inline fun <reified T, R> List<*>.letFor(letAction: (T) -> R): R? {
    val founded = find { it is T } ?: return null
    val foundedT = founded as T
    return letAction(foundedT)
}