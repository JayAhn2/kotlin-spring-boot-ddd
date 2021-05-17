package com.example.kotlinspringboot.modules.book.infrastructure.query

import com.example.kotlinspringboot.modules.book.dtos.BookDto

interface BookQueryRepository {

    fun findBookByTitle(title: String): BookDto

    fun findBookById(id: Long): BookDto

}
