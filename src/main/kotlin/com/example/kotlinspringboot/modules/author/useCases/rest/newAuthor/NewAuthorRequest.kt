package com.example.kotlinspringboot.modules.author.useCases.rest.newAuthor

data class NewAuthorRequest(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val biography: String?
)
