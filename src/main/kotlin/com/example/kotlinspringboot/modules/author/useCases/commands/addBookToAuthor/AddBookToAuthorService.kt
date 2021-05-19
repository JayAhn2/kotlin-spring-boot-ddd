package com.example.kotlinspringboot.modules.author.useCases.commands.addBookToAuthor

import com.example.kotlinspringboot.common.interfaces.UseCase
import com.example.kotlinspringboot.modules.author.infrastructure.persistence.AuthorPersistenceAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddBookToAuthorService(
    private val authorPersistenceAdapter: AuthorPersistenceAdapter
) : UseCase<AddBookToAuthorCommand, Unit> {
    override fun invoke(command: AddBookToAuthorCommand) {
        val author = authorPersistenceAdapter.findById(command.authorId)
        author.addBook(command)
        authorPersistenceAdapter.update(author)
    }
}
