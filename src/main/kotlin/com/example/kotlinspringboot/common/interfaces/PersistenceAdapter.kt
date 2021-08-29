package com.example.kotlinspringboot.common.interfaces

interface PersistenceAdapter<D, I> {
    fun findById(id: I): D
    fun insert(domain: D): D
    fun update(domain: D): D
    fun deleteById(id: I)
}
