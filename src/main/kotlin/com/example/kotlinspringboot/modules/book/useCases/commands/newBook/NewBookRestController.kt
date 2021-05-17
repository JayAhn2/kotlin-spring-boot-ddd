package com.example.kotlinspringboot.modules.book.useCases.commands.newBook

import com.example.kotlinspringboot.modules.book.dtos.BookDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class NewBookRestController(private val newBookService: NewBookService) {

    @PostMapping
    @ResponseBody
    fun newBook(@RequestBody newBookRequest: NewBookRequest): BookDto {
        return newBookService.invoke(newBookRequest)
    }
}
