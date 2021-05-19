package com.example.kotlinspringboot.modules.author.useCases.commands.newAuthor

data class NewAuthorRequest(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val biography: String?
)
