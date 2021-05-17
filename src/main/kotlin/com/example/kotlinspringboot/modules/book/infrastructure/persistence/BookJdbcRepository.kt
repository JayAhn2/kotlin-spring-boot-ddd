package com.example.kotlinspringboot.modules.book.infrastructure.persistence

import com.example.kotlinspringboot.core.jdbc.WithInsertAndUpdate
import org.springframework.data.repository.CrudRepository

interface BookJdbcRepository : CrudRepository<BookJdbcEntity, Long>, WithInsertAndUpdate<BookJdbcEntity> {}
