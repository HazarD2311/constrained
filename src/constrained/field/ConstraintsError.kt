package constrained.field

import constrained.base.Constraint
import constrained.constraints.string.NonEmptyConstraint
import constrained.constraints.string.PatternConstraint
import constrained.constraints.string.RangeConstraint

enum class ConstraintsError {

    NO_ERROR,
    IS_EMPTY,
    TOO_LONG,
    TOO_SHORT,
    INVALID_PATTERN;
}


fun List<Constraint<String>>.convert(): ConstraintsError {
    fun getSizeError(constraint: RangeConstraint): ConstraintsError =
            when (constraint.typeError) {
                RangeConstraint.Type.MIN -> ConstraintsError.TOO_SHORT
                RangeConstraint.Type.MAX -> ConstraintsError.TOO_LONG
                RangeConstraint.Type.UNKNOWN -> ConstraintsError.NO_ERROR
            }

    val invalidConstraint = firstOrNull() ?: return ConstraintsError.NO_ERROR

    return when (invalidConstraint) {
        is NonEmptyConstraint -> ConstraintsError.IS_EMPTY
        is RangeConstraint -> getSizeError(invalidConstraint)
        is PatternConstraint -> ConstraintsError.INVALID_PATTERN
        else -> ConstraintsError.NO_ERROR
    }
}