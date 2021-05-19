package com.example.kotlinspringboot.modules.author.infrastructure.persistence

import com.example.kotlinspringboot.core.jdbc.WithInsertAndUpdate
import org.springframework.data.repository.CrudRepository

interface AuthorJdbcRepository :
    CrudRepository<AuthorJdbcEntity, Long>,
    WithInsertAndUpdate<AuthorJdbcEntity> {
}
