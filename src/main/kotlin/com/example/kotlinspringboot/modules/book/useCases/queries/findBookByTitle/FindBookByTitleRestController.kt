package com.example.kotlinspringboot.modules.book.useCases.queries.findBookByTitle

import com.example.kotlinspringboot.modules.book.dtos.BookDto
import com.example.kotlinspringboot.modules.book.infrastructure.query.BookQueryRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class FindBookByTitleRestController(private val bookQueryRepository: BookQueryRepository) {

    @GetMapping
    @ResponseBody
    fun findBookByName(@RequestParam title: String): BookDto {
        return bookQueryRepository.findBookByTitle(title)
    }
}
