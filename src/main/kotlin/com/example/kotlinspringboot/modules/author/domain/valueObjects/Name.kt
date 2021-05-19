package com.example.kotlinspringboot.modules.author.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.validate

data class Name(val firstName: String, val lastName: String) {
    init {
        validate(this) {
            validate(Name::firstName).hasSize(1, 100)
            validate(Name::lastName).hasSize(1, 100)
        }
    }
}
