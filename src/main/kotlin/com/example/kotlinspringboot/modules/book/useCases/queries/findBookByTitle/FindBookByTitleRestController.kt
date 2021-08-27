package com.example.kotlinspringboot.modules.book.useCases.queries.findBookByTitle

import com.example.kotlinspringboot.common.constant.REST_BOOKS
import com.example.kotlinspringboot.modules.book.infrastructure.query.BookQueryRepository
import com.example.kotlinspringboot.modules.book.infrastructure.query.dtos.BookDto
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(REST_BOOKS)
@Api(tags = ["books"])
class FindBookByTitleRestController(private val bookQueryRepository: BookQueryRepository) {

    @GetMapping
    @ResponseBody
    fun findBookByName(@RequestParam title: String): BookDto {
        return bookQueryRepository.fetchByTitle(title)
    }
}
