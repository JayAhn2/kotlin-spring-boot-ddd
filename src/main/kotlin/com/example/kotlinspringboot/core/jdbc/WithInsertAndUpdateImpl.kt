package com.example.kotlinspringboot.core.jdbc

import org.springframework.data.jdbc.core.JdbcAggregateTemplate

class WithInsertAndUpdateImpl<T>(private val template: JdbcAggregateTemplate) :
    WithInsertAndUpdate<T> {
    override fun insert(t: T): T {
        return this.template.insert(t)
    }

    override fun update(t: T): T {
        return this.template.update(t)
    }
}
