package com.example.kotlinspringboot.modules.book.useCases.commands.deleteBook

import com.example.kotlinspringboot.common.constant.RestResourcePath
import com.example.kotlinspringboot.modules.book.domain.aggregate.BookId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RestResourcePath.BOOKS)
class DeleteBookRestController(private val deleteBookService: DeleteBookService) {

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteBook(@PathVariable id: Long) {
        val command = DeleteBookCommand(BookId(id))
        this.deleteBookService.invoke(command)
    }
}
