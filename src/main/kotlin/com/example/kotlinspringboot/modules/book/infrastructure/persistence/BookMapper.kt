package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.common.interfaces.ModelMapper
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title

object BookMapper : ModelMapper<Book, BookJdbcEntity> {

    override fun mapToDomainEntity(model: BookJdbcEntity): Book {
        return Book(
            BookId(model.id),
            Title(model.title),
            Isbn(model.isbn),
            Page(model.pages),
            model.authors.map { AuthorId(it.authorId) }.toSet()
        )
    }

    override fun mapToJdbcEntity(model: Book): BookJdbcEntity {
        return BookJdbcEntity(
            model.id.value,
            model.title.value,
            model.isbn.value,
            model.pages.value,
            model.authors.map { AuthorRef(it.value) }.toSet()
        )
    }
}
