package com.example.kotlinspringboot.modules.author.infrastructure.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("author")
data class AuthorJdbcEntity(
    @Id val id: Long,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val biography: String?,
    val bookIds: Set<BookRef> = hashSetOf()
)

@Table("book_author")
data class BookRef(val bookId: Long)
