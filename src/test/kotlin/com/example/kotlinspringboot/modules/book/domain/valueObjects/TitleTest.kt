package com.example.kotlinspringboot.modules.book.domain.valueObjects

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource
import org.valiktor.ConstraintViolationException

internal class TitleTest {

    @ParameterizedTest
    @ValueSource(strings = ["Sex and Character", "Republic", "Thus Spoke Zarathustra"])
    internal fun `constructor - should be constructed - when valid string`(value: String) {
        val result = Title(value)

        assertThat(value).isEqualTo(result.value)
    }

    @ParameterizedTest
    @EmptySource
    internal fun `constructor - should throw exception - when empty`(value: String) {
        assertThrows<ConstraintViolationException> { Title(value) }
    }
}
