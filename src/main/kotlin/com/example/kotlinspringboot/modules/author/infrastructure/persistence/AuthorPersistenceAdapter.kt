package com.example.kotlinspringboot.modules.author.infrastructure.persistence

import com.example.kotlinspringboot.common.exceptions.EntityNotFoundException
import com.example.kotlinspringboot.common.interfaces.PersistenceAdapter
import com.example.kotlinspringboot.modules.author.domain.aggregate.Author
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import org.springframework.stereotype.Service

@Service
class AuthorPersistenceAdapter(private val authorJdbcRepository: AuthorJdbcRepository) :
    PersistenceAdapter<Author, AuthorId> {
    override fun findById(id: AuthorId): Author {
        val author = authorJdbcRepository.findById(id.value)
        if (author.isEmpty) {
            throw EntityNotFoundException("The author is not found with id ${id.value}")
        }
        return AuthorMapper.mapToDomainEntity(author.get())
    }

    override fun insert(domain: Author): Author {
        val author = authorJdbcRepository.insert(AuthorMapper.mapToJdbcEntity(domain))
        return AuthorMapper.mapToDomainEntity(author)
    }

    override fun update(domain: Author): Author {
        val author = authorJdbcRepository.update(AuthorMapper.mapToJdbcEntity(domain))
        return AuthorMapper.mapToDomainEntity(author)
    }
}
