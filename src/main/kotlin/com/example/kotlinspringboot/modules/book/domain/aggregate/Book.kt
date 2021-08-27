package com.example.kotlinspringboot.modules.book.domain.aggregate

import com.example.kotlinspringboot.common.base.BaseAggregate
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Money
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import com.example.kotlinspringboot.modules.book.useCases.rest.addAuthor.AddAuthorCommand
import com.example.kotlinspringboot.modules.book.useCases.rest.newBook.NewBookCommand
import java.time.Year

data class Book(
    val id: BookId,
    var title: Title,
    var isbn: Isbn,
    var pages: Page,
    var price: Money,
    var publicationYear: Year,
    var authors: Set<AuthorId> = setOf()
) : BaseAggregate() {

    companion object {
        fun newBook(command: NewBookCommand): Book = Book(
            BookId.nextId(),
            command.title,
            command.isbn,
            command.pages,
            command.price,
            command.publicationYear,
            setOf()
        )
    }

    fun addAuthor(command: AddAuthorCommand) {
        val authors = authors.toMutableSet()
        authors.add(command.authorId)
        this.authors = authors
    }
}
