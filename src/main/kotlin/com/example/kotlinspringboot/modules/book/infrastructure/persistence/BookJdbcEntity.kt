package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("books")
data class BookJdbcEntity(
    @Id val id: Long,
    val title: String,
    val isbn: String,
    val pages: Int,
    val authorId: Long
)

