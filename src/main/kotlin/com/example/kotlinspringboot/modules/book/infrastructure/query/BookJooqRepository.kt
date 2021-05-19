package com.example.kotlinspringboot.modules.book.infrastructure.query

import com.example.kotlinspringboot.common.exceptions.RecordNotFoundException
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import com.example.kotlinspringboot.tables.Book
import com.example.kotlinspringboot.tables.BookAuthor
import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository

@Repository
class BookJooqRepository(private val ctx: DSLContext) : BookQueryRepository {
    private val book: Book = Book.BOOK
    private val bookAuthor: BookAuthor = BookAuthor.BOOK_AUTHOR
    private val selectBook =
        jsonObject(
            key("id").value(book.ID),
            key("title").value(book.TITLE),
            key("isbn").value(book.ISBN),
            key("pages").value(book.PAGES),
            key("authors").value(
                field(
                    select(jsonArrayAgg(bookAuthor.AUTHOR_ID))
                        .from(bookAuthor)
                        .where(bookAuthor.BOOK_ID.eq(book.ID))
                )
            )
        )

    override fun fetchByTitle(title: String): BookDto {
        return ctx.select(selectBook)
            .from(book)
            .where(book.TITLE.eq(title))
            .fetchOneInto(BookDto::class.java) ?: throw RecordNotFoundException()
    }

    override fun fetchById(id: Long): BookDto {
        return ctx.select(selectBook)
            .from(book)
            .where(book.ID.eq(id))
            .fetchOneInto(BookDto::class.java) ?: throw RecordNotFoundException()
    }
}
