package com.example.kotlinspringboot.core.snowflake

import java.time.Instant

class IdWorker(
    override val workerId: Long,
    override val snowflake: Snowflake = Snowflake(),
    val timeGen: () -> Long = { Instant.now().epochSecond }
) : SnowflakeId {

    private var lastTimestamp = -1L
    private var sequence = 0L

    init {
        snowflake.verifyWorkerId(workerId)
        snowflake.verifyTimestamp(timeGen())
    }

    @Synchronized
    override fun nextId(): Long {
        var timestamp = timeGen()

        require(timestamp >= lastTimestamp) {
            "Clock moved backwards. Refusing to generate id for ${lastTimestamp - timestamp} seconds"
        }

        if (lastTimestamp == timestamp) {
            sequence = snowflake.nextSequence(sequence)
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp)
            }
        } else {
            sequence = 0
        }

        lastTimestamp = timestamp
        return snowflake.get(timestamp, workerId, sequence)
    }

    private fun tilNextMillis(lastTimestamp: Long): Long =
        sequence { while (true) yield(timeGen()) }.first { it > lastTimestamp }

}
