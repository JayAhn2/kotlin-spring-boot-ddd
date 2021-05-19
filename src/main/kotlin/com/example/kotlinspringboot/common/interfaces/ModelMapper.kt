package com.example.kotlinspringboot.common.interfaces

interface ModelMapper<D, P> {
    fun mapToDomainEntity(model: P): D
    fun mapToJdbcEntity(model: D): P

}
