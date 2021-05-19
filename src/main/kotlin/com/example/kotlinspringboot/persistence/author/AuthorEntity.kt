package com.example.kotlinspringboot.persistence.author

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("author")
data class AuthorEntity(
    @Id val id: Long,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val biography: String?,
    @MappedCollection(idColumn = "author_id")
    val bookIds: Set<BookRef> = hashSetOf()
)

@Table("author_book")
data class BookRef(val bookId: Long)
