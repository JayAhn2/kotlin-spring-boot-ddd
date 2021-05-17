package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.common.exceptions.EntityNotFoundException
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import org.springframework.stereotype.Service


@Service
class BookPersistenceAdapter(private val bookJdbcRepository: BookJdbcRepository) {

    fun findById(bookId: BookId): Book {
        val result = bookJdbcRepository.findById(bookId.value)
        if (result.isEmpty) {
            throw EntityNotFoundException("The book is not found with id ${bookId.value}")
        }
        return BookMapper.mapToDomainEntity(result.get())
    }

    fun create(book: Book): Book {
        val result = bookJdbcRepository.insert(BookMapper.mapToJdbcEntity(book))
        return BookMapper.mapToDomainEntity(result)
    }

    fun update(book: Book): Book {
        val result = bookJdbcRepository.update(BookMapper.mapToJdbcEntity(book))
        return BookMapper.mapToDomainEntity(result)
    }

    fun deleteById(bookId: BookId) {
        bookJdbcRepository.deleteById(bookId.value)
    }
}
