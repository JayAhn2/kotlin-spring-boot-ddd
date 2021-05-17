package com.example.kotlinspringboot.modules.book.domain.aggregate

import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import com.example.kotlinspringboot.modules.book.useCases.commands.newBook.NewBookCommand

class Book(
    val id: BookId,
    val title: Title,
    val isbn: String,
    val pages: Int,
    val authorId: AuthorId
) {
    companion object {
        fun newBook(command: NewBookCommand) = Book(
            BookId.nextId(),
            command.title,
            command.isbn,
            command.pages,
            command.authorId
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Book(id=$id, title=$title, isbn='$isbn', pages=$pages, authorId=$authorId)"
    }
}
