package constrained.base

import java.io.Serializable

interface Constraint<T> : Serializable {

    /**
     * каким образом валидируется конкретное [value]
     * @return true если прошла валидацию, false в противном случае
     */
    fun isValid(value: T): Boolean
}