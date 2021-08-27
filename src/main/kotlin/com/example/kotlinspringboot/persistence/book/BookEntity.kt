package com.example.kotlinspringboot.persistence.book

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.Year

@Table("book")
data class BookEntity(
    @Id val id: Long,
    val title: String,
    val isbn: String,
    val pages: Int,
    val price: BigDecimal,
    @Column( "publication_year")
    val publicationYear: Int,
    @MappedCollection(idColumn = "book_id")
    val authors: Set<AuthorRef> = hashSetOf()
)

@Table("book_author")
data class AuthorRef(val authorId: Long)
