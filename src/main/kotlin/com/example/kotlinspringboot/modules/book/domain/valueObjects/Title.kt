package com.example.kotlinspringboot.modules.book.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

data class Title(
    val value: String
) {
    init {
        validate(this) {
            validate(Title::value).isNotBlank().hasSize(min = 1, max = 100)
        }
    }

    override fun toString(): String = value
}
