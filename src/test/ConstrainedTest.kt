package test

import constrained.Constrained
import constrained.constraints.string.MaxLengthConstraint
import constrained.constraints.string.MinLengthConstraint
import constrained.constraints.string.NonEmptyConstraint
import constrained.letFor
import constrained.stringConstrained
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ConstrainedTest {

    @Test
    fun testStingConstrained_NonEmpty() {
        val constrainedValue = Constrained("")
        assertTrue(constrainedValue.noError) //нет ошибок

        constrainedValue.add(NonEmptyConstraint()) //добавляем проверку на пустоту
        assertFalse(constrainedValue.noError) //теперь ошибка есть
        assertTrue(constrainedValue.validate().firstOrNull() is NonEmptyConstraint) //список непрошедших ошибок
    }

    @Test
    fun testStingConstrained_Range() {
        val constrained = stringConstrained() //внутри "" и NonEmptyConstraint
        constrained.add(MinLengthConstraint(3), MaxLengthConstraint(10)) //от 3 до 10
        assertTrue(constrained.setValueAnd("123456").noError)
        assertTrue(
                constrained.setValueAnd("12")
                        .validate()
                        .any { it is MinLengthConstraint }
        )//вызванная ошибка - MinLengthConstraint
        assertEquals(3,
                constrained.setValueAnd("12")
                        .validate()
                        .find { it is MinLengthConstraint }
                        .let { (it as MinLengthConstraint).min }
        )//достаем конкретный min, который не удовлетворил
        assertEquals(3,
                constrained.setValueAnd("12")
                        .validate()
                        .letFor<MinLengthConstraint, Int> { it.min }
        )//extension для списка .letFor //todo избавиться от Int через smartcast
        assertNull(
                constrained.setValueAnd("12345")
                        .validate()
                        .letFor<MinLengthConstraint, Int> { it.min }
        )
    }
}