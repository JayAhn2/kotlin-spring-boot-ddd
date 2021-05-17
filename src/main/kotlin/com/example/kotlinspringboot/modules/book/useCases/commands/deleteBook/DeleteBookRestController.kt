package com.example.kotlinspringboot.modules.book.useCases.commands.deleteBook

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class DeleteBookRestController(private val deleteBookService: DeleteBookService) {

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long) {
        this.deleteBookService.invoke(id)
    }
}
