package com.example.kotlinspringboot.modules.author.useCases.commands.addBookToAuthor

import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.domain.events.AuthorAddedToBookDomainEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AddBookToAuthorEventHandler(private val addBookToAuthorService: AddBookToAuthorService) {

    @EventListener
    fun handleEvent(event: AuthorAddedToBookDomainEvent) {
        val command = AddBookToAuthorCommand(BookId(event.bookId), AuthorId(event.authorId))
        this.addBookToAuthorService.invoke(command)
    }
}
