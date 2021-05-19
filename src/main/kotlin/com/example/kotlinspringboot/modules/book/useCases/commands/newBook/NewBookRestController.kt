package com.example.kotlinspringboot.modules.book.useCases.commands.newBook

import com.example.kotlinspringboot.common.constant.RestResourcePath
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Isbn
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Page
import com.example.kotlinspringboot.modules.book.domain.valueObjects.Title
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RestResourcePath.BOOKS)
@Api(tags = ["books"])
class NewBookRestController(private val newBookService: NewBookService) {

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun newBook(@RequestBody request: NewBookRequest): BookDto {
        val command = NewBookCommand(
            Title(request.title),
            Isbn(request.isbn),
            Page(request.pages)
        )
        return newBookService.invoke(command)
    }
}
