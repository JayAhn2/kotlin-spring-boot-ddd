package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title

object BookMapper {

    fun mapToDomainEntity(bookJdbcEntity: BookJdbcEntity): Book {
        return Book(
            BookId(bookJdbcEntity.id),
            Title(bookJdbcEntity.title),
            bookJdbcEntity.isbn,
            bookJdbcEntity.pages,
            AuthorId(bookJdbcEntity.authorId)
        )
    }

    fun mapToJdbcEntity(book: Book): BookJdbcEntity {
        return BookJdbcEntity(
            book.id.value,
            book.title.value,
            book.isbn,
            book.pages,
            book.authorId.value
        )
    }
}
