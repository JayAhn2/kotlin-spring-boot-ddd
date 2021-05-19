package com.example.kotlinspringboot.common.interfaces

interface UseCase<in C, out T> {
    fun invoke(command: C): T
}
