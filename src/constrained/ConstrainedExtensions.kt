package constrained

import constrained.constraints.string.NonEmptyConstraint

fun stringConstraint() = Constrained("")
        .apply { add(NonEmptyConstraint()) }