package com.example.kotlinspringboot.modules.book.useCases.commands.addAuthor

import com.example.kotlinspringboot.common.constant.RestResourcePath
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RestResourcePath.BOOKS)
class AddAuthorRestController(private val addAuthorService: AddAuthorService) {

    @PostMapping("{id}/authors")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun addAuthor(@PathVariable id: Long, @RequestBody request: AddAuthorRequest): BookDto {
        val command = AddAuthorCommand(BookId(id), AuthorId(request.authorId))
        return addAuthorService.invoke(command)
    }
}
