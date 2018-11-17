package constrained.constraints.number

import constrained.base.Constraint

class NonZeroConstraint : Constraint<Number> {

    override fun isValid(value: Number) = value == 0
}