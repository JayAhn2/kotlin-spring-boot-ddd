package com.example.kotlinspringboot.modules.author.useCases.rest.newAuthor

import com.example.kotlinspringboot.common.interfaces.UseCase
import com.example.kotlinspringboot.modules.author.domain.aggregate.Author
import com.example.kotlinspringboot.modules.author.infrastructure.persistence.AuthorPersistenceAdapter
import com.example.kotlinspringboot.modules.author.infrastructure.query.AuthorQueryRepository
import com.example.kotlinspringboot.modules.author.infrastructure.query.dtos.AuthorDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NewAuthorService(
    private val authorPersistenceAdapter: AuthorPersistenceAdapter,
    private val authorQueryRepository: AuthorQueryRepository
) : UseCase<NewAuthorCommand, AuthorDto> {
    override fun invoke(command: NewAuthorCommand): AuthorDto {
        val book = authorPersistenceAdapter.insert(Author.newAuthor(command))
        return authorQueryRepository.fetchById(book.id.value)
    }
}
