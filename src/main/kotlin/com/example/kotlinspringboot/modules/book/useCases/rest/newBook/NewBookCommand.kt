package com.example.kotlinspringboot.modules.book.useCases.rest.newBook

import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import java.time.Year

data class NewBookCommand(
    val title: Title,
    val isbn: Isbn,
    val pages: Page
) {}
