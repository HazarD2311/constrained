package test

import constrained.Constrained
import constrained.base.Constraint
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AnonymousConstraintTest {

    @Test
    fun testAnonymousConstraint_Int() {
        val constrained = Constrained(0,
                object : Constraint<Int> {
                    override fun isValid(value: Int): Boolean =
                            value == 3
                })
        constrained.add()
        assertTrue(constrained.setValueAnd(3).noError)
        assertFalse(constrained.setValueAnd(2).noError)
    }

    @Test
    fun testAnonymousConstraint_String() {
        val constrained = Constrained("",
                object : Constraint<String> {
                    override fun isValid(value: String): Boolean =
                            value == "Привет, мир!"
                })
        assertTrue(constrained.setValueAnd("Привет, мир!").noError)
        assertFalse(constrained.setValueAnd("Привет, планета!").noError)
    }
}