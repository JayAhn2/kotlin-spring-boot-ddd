package com.example.kotlinspringboot.core.snowflake

object IdGenerator {

    private val idWorker = IdWorker(1)

    fun nextId(): Long {
        return idWorker.nextId()
    }

}
