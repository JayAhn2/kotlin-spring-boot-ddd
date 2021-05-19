package com.example.kotlinspringboot.modules.author.infrastructure.query.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthorDto(
    @JsonProperty("id") val id: Long,
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String,
    @JsonProperty("age") val age: Int,
    @JsonProperty("books") val books: Set<Long>?
) {

}
