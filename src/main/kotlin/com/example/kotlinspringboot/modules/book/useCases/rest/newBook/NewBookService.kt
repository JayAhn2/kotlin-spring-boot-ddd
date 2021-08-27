package com.example.kotlinspringboot.modules.book.useCases.rest.newBook

import com.example.kotlinspringboot.common.interfaces.UseCase
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.infrastructure.persistence.BookPersistenceAdapter
import com.example.kotlinspringboot.modules.book.infrastructure.query.BookQueryRepository
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewBookService(
    private val bookPersistenceAdapter: BookPersistenceAdapter,
    private val bookQueryRepository: BookQueryRepository
) : UseCase<NewBookCommand, BookDto> {

    override fun invoke(command: NewBookCommand): BookDto {

        val savedBook = bookPersistenceAdapter.insert(Book.newBook(command))

        // should be replaced by jooq projection
        return bookQueryRepository.fetchById(savedBook.id.value)
    }
}
