package com.example.kotlinspringboot.modules.book.useCases.rest.newBook

data class NewBookRequest(
    val title: String,
    val isbn: String,
    val pages: Int
)
