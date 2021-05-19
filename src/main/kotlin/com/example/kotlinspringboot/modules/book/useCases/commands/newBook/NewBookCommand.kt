package com.example.kotlinspringboot.modules.book.useCases.commands.newBook

import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title

data class NewBookCommand(
    val title: Title,
    val isbn: Isbn,
    val pages: Page
)
