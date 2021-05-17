package com.example.kotlinspringboot.modules.book.useCases.commands.deleteBook

import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.infrastructure.persistence.BookPersistenceAdapter
import com.example.kotlinspringboot.modules.book.useCases.commands.UseCase
import org.springframework.stereotype.Service

@Service
class DeleteBookService(private val bookPersistenceAdapter: BookPersistenceAdapter) :
    UseCase<Long, Unit> {

    override fun invoke(request: Long) {
        bookPersistenceAdapter.deleteById(BookId(request))
    }
}
