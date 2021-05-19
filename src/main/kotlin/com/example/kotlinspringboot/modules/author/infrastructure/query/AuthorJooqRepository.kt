package com.example.kotlinspringboot.modules.author.infrastructure.query

import com.example.kotlinspringboot.common.exceptions.RecordNotFoundException
import com.example.kotlinspringboot.modules.author.infrastructure.query.dtos.AuthorDto
import com.example.kotlinspringboot.tables.Author
import com.example.kotlinspringboot.tables.AuthorBook
import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository

@Repository
class AuthorJooqRepository(private val ctx: DSLContext) : AuthorQueryRepository {
    private val author: Author = Author.AUTHOR
    private val authorBook: AuthorBook = AuthorBook.AUTHOR_BOOK
    private val selectAuthor =
        jsonObject(
            key("id").value(author.ID),
            key("firstName").value(author.FIRST_NAME),
            key("lastName").value(author.LAST_NAME),
            key("age").value(author.AGE),
            key("biography").value(author.BIOGRAPHY),
            key("books").value(
                field(
                    select(jsonArrayAgg(authorBook.BOOK_ID))
                        .from(authorBook)
                        .where(authorBook.AUTHOR_ID.eq(author.ID))
                )
            )
        )

    override fun fetchById(id: Long): AuthorDto {
        return ctx.select(selectAuthor)
            .from(author).where(author.ID.eq(id))
            .fetchOneInto(AuthorDto::class.java)
            ?: throw RecordNotFoundException("Author is not found by id: $id")
    }
}
