package com.example.kotlinspringboot.common.base

abstract class BaseDomainEvent(private val timestamp: Long = System.currentTimeMillis()) {}
