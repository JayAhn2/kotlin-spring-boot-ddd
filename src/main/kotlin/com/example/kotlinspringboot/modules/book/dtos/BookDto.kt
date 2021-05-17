package com.example.kotlinspringboot.modules.book.dtos

data class BookDto(
    val id: Long,
    val title: String,
    val isbn: String,
    val pages: Int,
    val authorId: Long
)
