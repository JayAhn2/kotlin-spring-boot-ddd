package com.example.kotlinspringboot.core.snowflake

interface SnowflakeId {
    val workerId: Long
    val snowflake: Snowflake
    fun nextId(): Long
}
