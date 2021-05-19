package com.example.kotlinspringboot.modules.author.domain.aggregate

import com.example.kotlinspringboot.common.base.BaseAggregate
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Age
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Biography
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Name
import com.example.kotlinspringboot.modules.author.useCases.commands.addBookToAuthor.AddBookToAuthorCommand
import com.example.kotlinspringboot.modules.author.useCases.commands.newAuthor.NewAuthorCommand
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId

data class Author(
    val id: AuthorId,
    var name: Name,
    var age: Age,
    var biography: Biography?,
    var bookIds: Set<BookId> = setOf()
) : BaseAggregate() {
    companion object {
        fun newAuthor(command: NewAuthorCommand): Author {
            return Author(
                AuthorId.nextId(),
                command.name,
                command.age,
                command.biography,
                setOf()
            )
        }
    }

    fun addBook(command: AddBookToAuthorCommand) {
        val bookIds = this.bookIds.toMutableSet();
        bookIds.add(command.bookId)
        this.bookIds = bookIds
    }
}
