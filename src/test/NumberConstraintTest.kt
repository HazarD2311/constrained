package test

import constrained.Constrained
import constrained.constraints.number.*
import constrained.constraints.number.bigdecimal.NonZeroBigDecimalConstraint
import constrained.constraints.number.bigdecimal.PositiveBigDecimalConstraint
import constrained.letFor
import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NumberConstraintTest {

    @Test
    fun testNonZeroConstraint() {
        //Int
        val intConstrained = Constrained(0, NonZeroConstraint())
        assertTrue(intConstrained.setValueAnd(1).noError)
        assertFalse(intConstrained.setValueAnd(0).noError)

        //Double
        val doubleConstrained = Constrained(0.0, NonZeroConstraint())
        assertTrue(doubleConstrained.setValueAnd(1.1).noError)
        assertFalse(doubleConstrained.setValueAnd(0.0).noError)
    }

    @Test
    fun testMinMaxConstraint() {
        val rangeConstraint = Constrained(0, MinConstraint(-3), MaxConstraint(6))
        assertTrue(rangeConstraint.setValueAnd(2).noError)
        assertFalse(rangeConstraint.setValueAnd(-5).noError)
        assertFalse(rangeConstraint.setValueAnd(9).noError)

        assertTrue(Constrained(2, PositiveNumberConstraint()).noError)
        assertTrue(Constrained(3.4, PositiveNumberConstraint()).noError)
        assertTrue(Constrained(-2, NegativeNumberConstraint()).noError)

        assertEquals(-3,
                rangeConstraint.setValueAnd(-10)
                        .validate()
                        .letFor<MinConstraint, Number> { it.min })
        assertEquals(MinConstraint(-3),
                rangeConstraint.setValueAnd(-10)
                        .validate()
                        .firstOrNull())
    }

    @Test
    fun testNonZeroConstraint_BigDecimal() {
        assertTrue(
                Constrained(
                        BigDecimal.ONE,
                        NonZeroBigDecimalConstraint()
                ).noError)
        assertFalse(
                Constrained(
                        BigDecimal.ZERO,
                        NonZeroBigDecimalConstraint()
                ).noError)
        assertTrue(
                Constrained(
                        BigDecimal.TEN,
                        PositiveBigDecimalConstraint()
                ).noError
        )
    }
}