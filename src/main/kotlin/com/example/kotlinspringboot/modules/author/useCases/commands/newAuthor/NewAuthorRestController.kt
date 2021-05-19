package com.example.kotlinspringboot.modules.author.useCases.commands.newAuthor

import com.example.kotlinspringboot.common.constant.RestResourcePath
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Age
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Biography
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Name
import com.example.kotlinspringboot.modules.author.infrastructure.query.dtos.AuthorDto
import io.swagger.annotations.Api
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(RestResourcePath.AUTHORS)
@Api(tags = ["authors"])
class NewAuthorRestController(private val newAuthorService: NewAuthorService) {

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun newAuthor(@RequestBody request: NewAuthorRequest): AuthorDto {
        val command = NewAuthorCommand(
            Name(request.firstName, request.lastName),
            Age(request.age),
            request.biography?.let { Biography(it) }
        )
        return newAuthorService.invoke(command)
    }
}
