package test

import constrained.Constrained
import constrained.constraints.string.MaxLengthConstraint
import constrained.constraints.string.MinLengthConstraint
import constrained.constraints.string.NonEmptyConstraint
import constrained.constraints.string.PatternConstraint
import constrained.letFor
import constrained.stringConstrained
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class StringConstraintTest {

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
        )//вызванная ошибка - MinLengthConstraint: способ 1
        assertEquals(
                MaxLengthConstraint(10),
                constrained.setValueAnd("12345678910")
                        .validate()
                        .firstOrNull()
        )//вызванная ошибка - MinLengthConstraint: способ 2
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

    @Test
    fun testStingConstrained_Pattern() {
        val stringConstrained = stringConstrained(PatternConstraint("^[0-9]+\$"))

        assertTrue(stringConstrained.setValueAnd("12322").noError)
        assertFalse(stringConstrained.setValueAnd("asd").noError)
        assertFalse(stringConstrained.setValueAnd("").noError)
    }
}