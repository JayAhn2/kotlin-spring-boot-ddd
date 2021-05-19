package com.example.kotlinspringboot.modules.author.infrastructure.query

import com.example.kotlinspringboot.modules.author.infrastructure.query.dtos.AuthorDto

interface AuthorQueryRepository {
    fun fetchById(id: Long): AuthorDto
}
