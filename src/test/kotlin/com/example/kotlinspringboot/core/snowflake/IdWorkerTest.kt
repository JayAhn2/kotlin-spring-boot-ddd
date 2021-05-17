package com.example.kotlinspringboot.core.snowflake

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class IdWorkerTest {

    @Test
    internal fun `generate an id`() {
        val s = IdWorker(1)
        val id: Long = s.nextId()

        assertThat(id).isGreaterThan(0)
    }

    @Test
    internal fun `return the correct job id`() {
        val s = IdWorker(1)
        assertThat(s.workerId).isEqualTo(1)
    }

    @Test
    internal fun `properly mask worker id`() {
        // arrange
        val workerId = 1L
        val worker = IdWorker(workerId)

        // act & assert
        (1..10000).map { worker.nextId() }
            .forEach {
                assertThat(worker.snowflake.getWorkerId(it)).isEqualTo(workerId)
            }
    }

    @Test
    internal fun `properly mask timestamp`() {
        // arrange
        val snowflake = Snowflake(timeBits = 31, workerBits = 1)
        val epochSecond = snowflake.spec().epochSecond
        val worker = IdWorker(workerId = 1, snowflake = snowflake, timeGen = { epochSecond })

        // act & assert
        (1..10000).map { worker.nextId() }.forEach {
            assertThat(worker.snowflake.spec(it).timestamp).isEqualTo(epochSecond)
        }
    }

    @Test
    internal fun `roll over sequence id`() {
        // arrange
        val snowflake = Snowflake(timeBits = 31, workerBits = 31) // seqBits = 64 -1 -31 -31
        val worker = IdWorker(workerId = 1, snowflake = snowflake)

        // act & assert
        (1..3).map { worker.nextId() }.forEach {
            assertThat(worker.snowflake.getWorkerId(it)).isEqualTo(1)
        }
    }

    @Test
    internal fun `generate sequential ids`() {
        // arrange
        val worker = IdWorker(1)
        var lastId = 0L

        // act & assert
        (1..100).map { worker.nextId() }.forEach {
            assertThat(it).isGreaterThan(lastId)
            lastId = it
        }
    }

    @Test
    internal fun `generate 1 million ids quickly`() {
        // arrange
        val worker = IdWorker(1)
        val startAt = System.currentTimeMillis()
        (1..1000000).map { worker.nextId() }
        val endAt = System.currentTimeMillis()
        println("generate 1000000 ids in ${endAt - startAt} ms")

        assertThat(1).isGreaterThan(0)
    }

    @Test
    internal fun `sleep if we would rollover twice in the same millisecond`() {
        // arrange
        class LimitedTimeWorker {
            val snowflake = Snowflake(timeBits = 31, workerBits = 31)
            var time = snowflake.spec().epochSecond + 1
            var queue = (0..9999).map { time }.let { it + (time + 1) }.listIterator()
            private val worker = IdWorker(0, snowflake = snowflake, timeGen = { queue.next() })

            fun nextId() = worker.nextId()
        }

        // act & assert
        val worker = LimitedTimeWorker()

        worker.nextId()
        worker.nextId()
        worker.nextId()

        assertThat(worker.queue.hasNext()).isFalse
    }

    @Test
    internal fun `generate only unique ids`() {
        // arrange
        val worker = IdWorker(1)
        val n = 2000000

        // act & assert
        val distinct = (1..n).map { worker.nextId() }.distinct()

        assertThat(distinct.size).isEqualTo(n)
    }

    @Test
    internal fun `generate only unique ids, even when time goes backwards`() {
        class StaticTimeWorker {
            val snowflake = Snowflake()
            var time = snowflake.spec().epochSecond + 1
            private val worker = IdWorker(0, timeGen = { time })
            fun nextId() = worker.nextId()
        }

        val worker = StaticTimeWorker()

        // reported at https://github.com/twitter/snowflake/issues/6
        // first we generate 2 ids with the same time, so that we get the sequence to 1
        val id1 = worker.nextId()
        assertThat(worker.snowflake.getDeltaSeconds(id1)).isEqualTo(1)
        assertThat(worker.snowflake.getSequence(id1)).isEqualTo(0)

        val id2 = worker.nextId()
        assertThat(worker.snowflake.getDeltaSeconds(id2)).isEqualTo(1)
        assertThat(worker.snowflake.getSequence(id2)).isEqualTo(1)

        worker.time = worker.snowflake.spec().epochSecond
        val exception = assertThrows<IllegalArgumentException> {
            worker.nextId()
        }

        assertThat(exception.message).isEqualTo("Clock moved backwards. Refusing to generate id for 1 seconds")

        worker.time = worker.snowflake.spec().epochSecond + 1
        val id3 = worker.nextId()
        assertThat(worker.snowflake.getDeltaSeconds(id3)).isEqualTo(1)
        assertThat(worker.snowflake.getSequence(id3)).isEqualTo(2)
    }
}

