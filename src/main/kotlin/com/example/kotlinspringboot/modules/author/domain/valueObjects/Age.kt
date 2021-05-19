package com.example.kotlinspringboot.modules.author.domain.valueObjects

import org.valiktor.functions.isPositiveOrZero
import org.valiktor.validate

data class Age(val value: Int) {
    init {
        validate(this) {
            validate(Age::value).isPositiveOrZero()
        }
    }
}
