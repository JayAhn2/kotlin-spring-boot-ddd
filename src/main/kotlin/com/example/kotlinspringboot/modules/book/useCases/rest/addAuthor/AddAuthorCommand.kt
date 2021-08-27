package com.example.kotlinspringboot.modules.book.useCases.rest.addAuthor

import com.example.kotlinspringboot.common.base.BaseCommand
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId

data class AddAuthorCommand(val bookId: BookId, val authorId: AuthorId) : BaseCommand()
