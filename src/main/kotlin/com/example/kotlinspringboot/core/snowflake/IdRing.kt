package com.example.kotlinspringboot.core.snowflake

import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

class IdRing(
    override val workerId: Long,
    override val snowflake: Snowflake = Snowflake(),
    ringBits: Int = 9, //512
    private val timeGen: () -> Long = { Instant.now().epochSecond }
) : SnowflakeId {

    private val ringSize: Int = 1 shl ringBits
    private val ringMask: Long = (ringSize - 1).toLong()

    private var ring: Array<AtomicLong>
    private val ringIndex = AtomicLong(0)

    init {
        require(ringBits < snowflake.spec().seqBits) {
            "ringBits[$ringBits] can't be greater or equal to timeBits[${snowflake.spec().seqBits}]"
        }
        snowflake.verifyWorkerId(workerId)
        val timestamp = timeGen()
        snowflake.verifyTimestamp(timestamp)

        ring = Array(ringSize) {
            AtomicLong(snowflake.get(timestamp, workerId, ringIndex.getAndIncrement()))
        }
        ringIndex.set(0)
    }

    override fun nextId(): Long {
        val index = (ringIndex.getAndIncrement() and ringMask).toInt()
        return ring[index].updateAndGet { nextId(it, ring.size) }
    }

    private fun nextId(last: Long, step: Int): Long {
        val lastSequence = snowflake.getSequence(last)
        val workerId = workerId
        var deltaSeconds = snowflake.getDeltaSeconds(last).coerceAtLeast(snowflake.delta(timeGen()))

        val sequence = snowflake.nextSequence(lastSequence, step)
        if (sequence < lastSequence) {
            deltaSeconds += 1
        }

        return snowflake.combine(deltaSeconds, workerId, sequence)
    }

}
