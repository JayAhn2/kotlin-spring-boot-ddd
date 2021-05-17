package com.example.kotlinspringboot.core.jdbc

interface WithInsertAndUpdate<T> {
    fun insert(t: T): T
    fun update(t: T): T
}
