package com.example.kotlinspringboot.modules.book.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.functions.isPositive
import org.valiktor.functions.isPositiveOrZero
import org.valiktor.validate
import java.math.BigDecimal

data class Money(val value: BigDecimal) {
    init {
        validate(this) {
            validate(Money::value).isPositive()
        }
    }
}
