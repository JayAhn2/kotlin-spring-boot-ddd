package com.example.kotlinspringboot.modules.book.useCases.commands.newBook

import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import com.example.kotlinspringboot.modules.book.dtos.BookDto
import com.example.kotlinspringboot.modules.book.infrastructure.persistence.BookPersistenceAdapter
import com.example.kotlinspringboot.modules.book.infrastructure.query.BookQueryRepository
import com.example.kotlinspringboot.modules.book.useCases.commands.UseCase
import org.springframework.stereotype.Service

@Service
class NewBookService(
    private val bookPersistenceAdapter: BookPersistenceAdapter,
    private val bookQueryRepository: BookQueryRepository
) :
    UseCase<NewBookRequest, BookDto> {

    override fun invoke(request: NewBookRequest): BookDto {
        val command = NewBookCommand(
            Title(request.title),
            request.isbn,
            request.pages,
            AuthorId(request.authorId)
        )
        val savedBook = bookPersistenceAdapter.create(Book.newBook(command))

        // should be replaced by jooq projection
        return bookQueryRepository.findBookById(savedBook.id.value)
    }
}
