package com.example.kotlinspringboot.modules.book.infrastructure.query

import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto

interface BookQueryRepository {

    fun fetchByTitle(title: String): BookDto

    fun fetchById(id: Long): BookDto

}
