package com.example.kotlinspringboot.modules.book.domain.events

import com.example.kotlinspringboot.common.event.DomainEvent

data class AuthorAddedToBookEvent(
    val bookId: Long,
    val authorId: Long
) : DomainEvent()
