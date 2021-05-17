package com.example.kotlinspringboot.modules.book.useCases.commands

interface UseCase<in P, out T> {
    fun invoke(request: P): T
}
