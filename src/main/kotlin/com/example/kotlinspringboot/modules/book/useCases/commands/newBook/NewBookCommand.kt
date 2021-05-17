package com.example.kotlinspringboot.modules.book.useCases.commands.newBook

import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title

data class NewBookCommand(
    val title: Title,
    val isbn: String,
    val pages: Int,
    val authorId: AuthorId
)
