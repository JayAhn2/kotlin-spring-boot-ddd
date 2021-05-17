package com.example.kotlinspringboot.modules.book.infrastructure.query

import com.example.kotlinspringboot.modules.book.dtos.BookDto
import com.example.kotlinspringboot.modules.book.infrastructure.persistence.BookJdbcEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface BookQueryRepository : CrudRepository<BookJdbcEntity, Long> {

    @Query(
        """
        select b.id        as id,
               b.title     as title,
               b.isbn      as isbn,
               b.pages     as pages,
               b.author_id as authorId
        from books b
        where b.title = :title
        """
    )
    fun findBookByTitle(title: String): BookDto

    @Query(
        """
        select b.id        as id,
               b.title     as title,
               b.isbn      as isbn,
               b.pages     as pages,
               b.author_id as authorId
        from books b
        where b.id = :id
        """
    )
    fun findBookById(id: Long): BookDto

}
