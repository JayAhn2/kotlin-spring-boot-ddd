package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.common.exceptions.EntityNotFoundException
import com.example.kotlinspringboot.common.interfaces.ModelMapper
import com.example.kotlinspringboot.common.interfaces.PersistenceAdapter
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.Book
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import com.example.kotlinspringboot.persistence.book.AuthorRef
import com.example.kotlinspringboot.persistence.book.BookEntity
import com.example.kotlinspringboot.persistence.book.BookRepository
import org.springframework.stereotype.Service


@Service
class BookPersistenceAdapter(private val bookRepository: BookRepository) :
    PersistenceAdapter<Book, BookId> {

    override fun findById(id: BookId): Book {
        val result = bookRepository.findById(id.value)
        if (result.isEmpty) {
            throw EntityNotFoundException("The book is not found with id ${id.value}")
        }
        return BookMapper.mapToDomainEntity(result.get())
    }

    override fun insert(domain: Book): Book {
        val result = bookRepository.insert(BookMapper.mapToJdbcEntity(domain))
        return BookMapper.mapToDomainEntity(result)
    }

    override fun update(domain: Book): Book {
        val result = bookRepository.update(BookMapper.mapToJdbcEntity(domain))
        return BookMapper.mapToDomainEntity(result)
    }

    fun deleteById(bookId: BookId) {
        bookRepository.deleteById(bookId.value)
    }
}

object BookMapper : ModelMapper<Book, BookEntity> {

    override fun mapToDomainEntity(model: BookEntity): Book {
        return Book(
            BookId(model.id),
            Title(model.title),
            Isbn(model.isbn),
            Page(model.pages),
            model.authors.map { AuthorId(it.authorId) }.toSet()
        )
    }

    override fun mapToJdbcEntity(model: Book): BookEntity {
        return BookEntity(
            model.id.value,
            model.title.value,
            model.isbn.value,
            model.pages.value,
            model.authors.map { AuthorRef(it.value) }.toSet()
        )
    }
}
