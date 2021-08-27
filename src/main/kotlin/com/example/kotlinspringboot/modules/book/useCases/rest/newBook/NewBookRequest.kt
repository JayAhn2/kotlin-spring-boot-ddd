package com.example.kotlinspringboot.modules.book.useCases.rest.newBook

import java.math.BigDecimal
import java.time.Year

data class NewBookRequest(
    val title: String,
    val isbn: String,
    val pages: Int,
    val price: BigDecimal,
    val publicationYear: Int
)
