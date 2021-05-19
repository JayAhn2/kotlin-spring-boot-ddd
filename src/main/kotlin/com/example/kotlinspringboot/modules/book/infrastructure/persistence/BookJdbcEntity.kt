package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("book")
data class BookJdbcEntity(
    @Id val id: Long,
    val title: String,
    val isbn: String,
    val pages: Int,
    @MappedCollection(idColumn = "book_id")
    val authors: Set<AuthorRef> = hashSetOf()
)

@Table("book_author")
data class AuthorRef(val authorId: Long)
