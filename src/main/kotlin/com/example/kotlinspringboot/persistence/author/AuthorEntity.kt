package com.example.kotlinspringboot.persistence.author

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("author")
data class AuthorEntity(
    @Id val id: Long,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val biography: String?,
    val bookIds: Set<BookRef> = hashSetOf()
)

@Table("book_author")
data class BookRef(val bookId: Long)
