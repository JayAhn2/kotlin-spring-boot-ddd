package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.common.exceptions.EntityNotFoundException
import com.example.kotlinspringboot.common.interfaces.PersistenceAdapter
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import org.springframework.stereotype.Service


@Service
class BookPersistenceAdapter(private val bookJdbcRepository: BookJdbcRepository) :
    PersistenceAdapter<Book, BookId> {

    override fun findById(id: BookId): Book {
        val result = bookJdbcRepository.findById(id.value)
        if (result.isEmpty) {
            throw EntityNotFoundException("The book is not found with id ${id.value}")
        }
        return BookMapper.mapToDomainEntity(result.get())
    }

    override fun insert(domain: Book): Book {
        val result = bookJdbcRepository.insert(BookMapper.mapToJdbcEntity(domain))
        return BookMapper.mapToDomainEntity(result)
    }

    override fun update(domain: Book): Book {
        val result = bookJdbcRepository.update(BookMapper.mapToJdbcEntity(domain))
        return BookMapper.mapToDomainEntity(result)
    }

    fun deleteById(bookId: BookId) {
        bookJdbcRepository.deleteById(bookId.value)
    }
}
