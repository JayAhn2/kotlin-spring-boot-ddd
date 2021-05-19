package com.example.kotlinspringboot.modules.author.useCases.commands.addBookToAuthor

import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId

data class AddBookToAuthorCommand(val bookId: BookId, val authorId: AuthorId)
