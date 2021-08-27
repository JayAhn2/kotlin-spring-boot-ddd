package com.example.kotlinspringboot.modules.book.useCases.rest.deleteBook

import com.example.kotlinspringboot.common.interfaces.UseCase
import com.example.kotlinspringboot.modules.book.infrastructure.persistence.BookPersistenceAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteBookService(private val bookPersistenceAdapter: BookPersistenceAdapter) :
    UseCase<DeleteBookCommand, Unit> {

    override fun invoke(command: DeleteBookCommand) {
        bookPersistenceAdapter.deleteById(command.bookId)
    }
}
