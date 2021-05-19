package com.example.kotlinspringboot.modules.author.infrastructure.persistence

import com.example.kotlinspringboot.common.exceptions.EntityNotFoundException
import com.example.kotlinspringboot.common.interfaces.ModelMapper
import com.example.kotlinspringboot.common.interfaces.PersistenceAdapter
import com.example.kotlinspringboot.modules.author.domain.aggregate.Author
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Age
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Biography
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Name
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.persistence.author.AuthorEntity
import com.example.kotlinspringboot.persistence.author.AuthorRepository
import com.example.kotlinspringboot.persistence.author.BookRef
import org.springframework.stereotype.Service

@Service
class AuthorPersistenceAdapter(private val authorRepository: AuthorRepository) :
    PersistenceAdapter<Author, AuthorId> {
    override fun findById(id: AuthorId): Author {
        val author = authorRepository.findById(id.value)
        if (author.isEmpty) {
            throw EntityNotFoundException("The author is not found with id ${id.value}")
        }
        return AuthorMapper.mapToDomainEntity(author.get())
    }

    override fun insert(domain: Author): Author {
        val author = authorRepository.insert(AuthorMapper.mapToJdbcEntity(domain))
        return AuthorMapper.mapToDomainEntity(author)
    }

    override fun update(domain: Author): Author {
        val author = authorRepository.update(AuthorMapper.mapToJdbcEntity(domain))
        return AuthorMapper.mapToDomainEntity(author)
    }
}

object AuthorMapper : ModelMapper<Author, AuthorEntity> {
    override fun mapToDomainEntity(model: AuthorEntity): Author {
        return Author(
            AuthorId(model.id),
            Name(model.firstName, model.lastName),
            Age(model.age),
            model.biography?.let { Biography(it) },
            model.bookIds.map { BookId(it.bookId) }.toSet()
        )
    }

    override fun mapToJdbcEntity(model: Author): AuthorEntity {
        return AuthorEntity(
            model.id.value,
            model.name.firstName,
            model.name.lastName,
            model.age.value,
            model.biography?.value,
            model.bookIds.map { BookRef(it.value) }.toSet()
        )
    }
}
