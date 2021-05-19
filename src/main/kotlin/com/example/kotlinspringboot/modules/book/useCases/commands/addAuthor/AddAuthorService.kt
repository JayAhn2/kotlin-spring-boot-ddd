package com.example.kotlinspringboot.modules.book.useCases.commands.addAuthor

import com.example.kotlinspringboot.common.interfaces.UseCase
import com.example.kotlinspringboot.modules.book.domain.events.AuthorAddedToBookEvent
import com.example.kotlinspringboot.modules.book.infrastructure.persistence.BookPersistenceAdapter
import com.example.kotlinspringboot.modules.book.infrastructure.query.BookQueryRepository
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddAuthorService(
    private val bookPersistenceAdapter: BookPersistenceAdapter,
    private val bookQueryRepository: BookQueryRepository,
    private val publisher: ApplicationEventPublisher
) : UseCase<AddAuthorCommand, BookDto> {
    override fun invoke(command: AddAuthorCommand): BookDto {
        val book = bookPersistenceAdapter.findById(command.bookId)
        book.addAuthor(command)
        bookPersistenceAdapter.update(book)

        // This event publish should be done inside of the domain.
        this.publisher.publishEvent(
            AuthorAddedToBookEvent(book.id.value, command.authorId.value)
        )

        return bookQueryRepository.fetchById(book.id.value)
    }
}
