package com.example.kotlinspringboot.modules.book.useCases.rest.addAuthor

import com.example.kotlinspringboot.common.constant.REST_BOOKS
import com.example.kotlinspringboot.modules.author.domain.aggregate.AuthorId
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(REST_BOOKS)
@Api(tags = ["books"])
class AddAuthorRestController(private val addAuthorService: AddAuthorService) {

    @PostMapping("{id}/authors/{authorId}")
    @ResponseBody
    @ApiOperation(value = "Display greeting message to admin user", response = BookDto::class)
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun addAuthor(@PathVariable id: Long, @PathVariable authorId: Long): BookDto {
        val command = AddAuthorCommand(BookId(id), AuthorId(authorId))
        return addAuthorService.invoke(command)
    }
}
