package com.example.kotlinspringboot.persistence.author

import com.example.kotlinspringboot.core.jdbc.WithInsertAndUpdate
import org.springframework.data.repository.CrudRepository

interface AuthorRepository :
    CrudRepository<AuthorEntity, Long>,
    WithInsertAndUpdate<AuthorEntity> {
}
