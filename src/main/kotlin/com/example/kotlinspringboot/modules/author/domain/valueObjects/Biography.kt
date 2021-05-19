package com.example.kotlinspringboot.modules.author.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.validate

data class Biography(val value: String) {
    init {
        validate(this) {
            validate(Biography::value).hasSize(1, 3000)
        }
    }
}
