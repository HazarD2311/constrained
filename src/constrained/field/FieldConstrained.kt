package constrained.field

import constrained.Constrained
import constrained.base.Constraint
import constrained.base.IConstrained
import constrained.constraints.string.RangeConstraint

/**
 * Помечает строковое поле,
 * как имеющее какие-либо ограничения.
 *
 * В качестве главное особенности:
 * * удобный биндинг полей по [fieldName]
 */
data class FieldConstrained(val fieldName: String, val constrained: Constrained<String>) : IConstrained<String> {

    override val noError = isValid() == ConstraintsError.NO_ERROR

    override fun validate() = constrained.validate()

    override fun setValueAnd(value: String) = this.apply { setValue(value) }

    override fun setValue(value: String) {
        constrained.setValue(value)
    }

    override fun getValue(): String = constrained.getValue()

    fun bindField(fields: Map<String, List<Constraint<String>>>) {
        val foundedConstraints = fields[fieldName]
                ?: emptyList()
        constrained.addAll(foundedConstraints)
    }

    fun isValid(): ConstraintsError = validate().convert()
}

/**
 * Интерфейсом помечается класс
 * для биндинга из вне поля
 */
interface WithConstrainedFields {
    fun bindFields(fields: Map<String, List<Constraint<String>>>)
}

fun Constrained<String>.asField(fieldName: String) =
        FieldConstrained(fieldName, this)

fun FieldConstrained.getRangeConstraint(): RangeConstraint? {
    constrained.constraints.forEach {
        if (it is RangeConstraint) return it
    }
    return null
}