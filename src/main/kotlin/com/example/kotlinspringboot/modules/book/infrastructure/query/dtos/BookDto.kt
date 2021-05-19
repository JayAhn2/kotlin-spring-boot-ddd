package com.example.kotlinspringboot.modules.book.infrastructure.query.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class BookDto(
    @JsonProperty("id") val id: Long,
    @JsonProperty("title") val title: String,
    @JsonProperty("isbn") val isbn: String,
    @JsonProperty("pages") val pages: Int,
    @JsonProperty("authors") val authors: Set<Long>?
)
