package com.example.kotlinspringboot.modules.book.domain.valueObjects

import org.valiktor.functions.isPositive
import org.valiktor.validate

data class Page(val value: Int) {
    init {
        validate(this) {
            validate(Page::value).isPositive()
        }
    }
}
