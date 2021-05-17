package com.example.kotlinspringboot.modules.book.domain.aggregate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.valiktor.ConstraintViolationException

internal class BookIdTest {

    @Test
    internal fun `nextId - should generate id`() {
        // arrange
        val result = BookId.nextId()

        assertThat(result).isNotNull
    }

    @Test
    internal fun `of - should throw exception - when zero`() {
        assertThrows<ConstraintViolationException> {
            BookId(0)
        }
    }
}
