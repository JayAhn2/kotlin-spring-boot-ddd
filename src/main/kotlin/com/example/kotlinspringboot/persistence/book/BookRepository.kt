package com.example.kotlinspringboot.persistence.book

import com.example.kotlinspringboot.core.jdbc.WithInsertAndUpdate
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookEntity, Long>, WithInsertAndUpdate<BookEntity> {}
