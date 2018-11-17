package constrained

import constrained.constraints.string.NonEmptyConstraint

fun stringConstrained() = Constrained("")
        .apply { add(NonEmptyConstraint()) }

inline fun <reified T, R> List<*>.letFor(letAction: (T) -> R): R? {
    val founded = find { it is T } ?: return null
    val foundedT = founded as T
    return letAction(foundedT)
}