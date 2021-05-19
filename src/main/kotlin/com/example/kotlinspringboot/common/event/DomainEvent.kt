package com.example.kotlinspringboot.common.event

abstract class DomainEvent(private val timestamp: Long = System.currentTimeMillis()) {}
