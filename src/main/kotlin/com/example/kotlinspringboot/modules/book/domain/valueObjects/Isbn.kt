package com.example.kotlinspringboot.modules.book.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.validate

data class Isbn(val value: String) {
    init {
        validate(this) {
            validate(Isbn::value).hasSize(10, 10)
        }
    }
}
