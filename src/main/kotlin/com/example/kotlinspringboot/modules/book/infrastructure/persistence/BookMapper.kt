package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.common.interfaces.ModelMapper
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Money
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import com.example.kotlinspringboot.persistence.book.AuthorRef
import com.example.kotlinspringboot.persistence.book.BookEntity
import java.time.Year

object BookMapper : ModelMapper<Book, BookEntity> {

    override fun mapToDomainEntity(model: BookEntity): Book {
        return Book(
            BookId(model.id),
            Title(model.title),
            Isbn(model.isbn),
            Page(model.pages),
            Money(model.price),
            Year.of(model.publicationYear),
            model.authors.map { AuthorId(it.authorId) }.toSet()
        )
    }

    override fun mapToJdbcEntity(model: Book): BookEntity {
        return BookEntity(
            model.id.value,
            model.title.value,
            model.isbn.value,
            model.pages.value,
            model.price.value,
            model.publicationYear.value,
            model.authors.map { AuthorRef(it.value) }.toSet()
        )
    }
}
