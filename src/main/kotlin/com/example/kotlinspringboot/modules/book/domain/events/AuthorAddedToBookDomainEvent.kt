package com.example.kotlinspringboot.modules.book.domain.events

import com.example.kotlinspringboot.common.base.BaseDomainEvent

data class AuthorAddedToBookDomainEvent(
    val bookId: Long,
    val authorId: Long
) : BaseDomainEvent()
