package com.example.kotlinspringboot.common.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
class EntityAlreadyExistException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
}
