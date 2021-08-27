package com.example.kotlinspringboot.modules.book.useCases.commands.deleteBook

import com.example.kotlinspringboot.common.constant.REST_BOOKS
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(REST_BOOKS)
@Api(tags = ["books"])
class DeleteBookRestController(private val deleteBookService: DeleteBookService) {

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteBook(@PathVariable id: Long) {
        val command = DeleteBookCommand(BookId(id))
        this.deleteBookService.invoke(command)
    }
}
