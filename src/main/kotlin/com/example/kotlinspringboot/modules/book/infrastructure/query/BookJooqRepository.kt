package com.example.kotlinspringboot.modules.book.infrastructure.query

import com.example.kotlinspringboot.common.exceptions.RecordNotFoundException
import com.example.kotlinspringboot.modules.book.dtos.BookDto
import com.example.kotlinspringboot.tables.Books
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class BookJooqRepository(private val ctx: DSLContext) : BookQueryRepository {
    val book: Books = Books.BOOKS

    override fun findBookByTitle(title: String): BookDto {
        return ctx.select(book.ID, book.TITLE, book.ISBN, book.PAGES, book.AUTHOR_ID)
            .from(book)
            .where(book.TITLE.eq(title))
            .fetchOneInto(BookDto::class.java) ?: throw RecordNotFoundException()
    }

    override fun findBookById(id: Long): BookDto {
        return ctx.select(book.ID, book.TITLE, book.ISBN, book.PAGES, book.AUTHOR_ID)
            .from(book)
            .where(book.ID.eq(id))
            .fetchOneInto(BookDto::class.java) ?: throw RecordNotFoundException()
    }
}
