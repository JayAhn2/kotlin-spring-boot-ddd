package com.example.kotlinspringboot.modules.author.infrastructure.persistence

import com.example.kotlinspringboot.common.interfaces.ModelMapper
import com.example.kotlinspringboot.modules.author.domain.aggregate.Author
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Age
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Biography
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Name
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId

object AuthorMapper : ModelMapper<Author, AuthorJdbcEntity> {
    override fun mapToDomainEntity(model: AuthorJdbcEntity): Author {
        return Author(
            AuthorId(model.id),
            Name(model.firstName, model.lastName),
            Age(model.age),
            model.biography?.let { Biography(it) },
            model.bookIds.map { BookId(it.bookId) }.toSet()
        )
    }

    override fun mapToJdbcEntity(model: Author): AuthorJdbcEntity {
        return AuthorJdbcEntity(
            model.id.value,
            model.name.firstName,
            model.name.lastName,
            model.age.value,
            model.biography?.value,
            model.bookIds.map { BookRef(it.value) }.toSet()
        )
    }
}
