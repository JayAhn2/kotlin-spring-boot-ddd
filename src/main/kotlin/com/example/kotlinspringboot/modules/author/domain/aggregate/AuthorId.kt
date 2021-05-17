package com.example.kotlinspringboot.modules.author.domain.aggregate

import com.example.kotlinspringboot.core.snowflake.IdGenerator
import org.valiktor.functions.isNotZero
import org.valiktor.validate

data class AuthorId(
    val value: Long
) {
    init {
        validate(this) {
            validate(AuthorId::value).isNotZero()
        }
    }

    companion object {
        fun nextId() = AuthorId(IdGenerator.nextId())
    }
}
