package com.example.kotlinspringboot.modules.book.domain.aggregate

import com.example.kotlinspringboot.core.snowflake.IdGenerator
import org.valiktor.functions.isNotZero
import org.valiktor.validate

data class BookId(
    val value: Long
) {
    init {
        validate(this) {
            validate(BookId::value).isNotZero()
        }
    }

    companion object {
        fun nextId() = BookId(IdGenerator.nextId())
    }
}
