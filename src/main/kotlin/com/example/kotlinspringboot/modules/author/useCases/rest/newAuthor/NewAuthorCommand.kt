package com.example.kotlinspringboot.modules.author.useCases.rest.newAuthor

import com.example.kotlinspringboot.common.base.BaseCommand
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Age
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Biography
import com.example.kotlinspringboot.modules.author.domain.valueObjects.Name

data class NewAuthorCommand(
    val name: Name,
    val age: Age,
    val biography: Biography?
) : BaseCommand()
