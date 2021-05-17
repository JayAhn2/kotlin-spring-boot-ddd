package com.example.kotlinspringboot.modules.book.useCases.commands.newBook

data class NewBookRequest(
    val title: String,
    val isbn: String,
    val pages: Int,
    val authorId: Long
)
