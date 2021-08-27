package com.example.kotlinspringboot.modules.book.useCases.rest.deleteBook

import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId

data class DeleteBookCommand(val bookId: BookId)
