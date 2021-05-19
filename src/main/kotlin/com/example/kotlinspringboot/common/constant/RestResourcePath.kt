package com.example.kotlinspringboot.common.constant

class RestResourcePath {
    companion object {
        private const val ROOT = "/api"
        const val BOOKS = "$ROOT/books"
        const val AUTHORS = "$ROOT/authors"
    }
}
