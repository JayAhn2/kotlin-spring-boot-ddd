package com.example.kotlinspringboot.core.snowflake

import java.time.Instant

class Snowflake(
    private val timeBits: Int = 30,
    private val workerBits: Int = 10,
    private val timeEpoch: String = "2021-01-01T00:00:00Z"
) {
    private val epochSecond: Long = Instant.parse(timeEpoch).epochSecond

    private val seqBits: Int = 64 - 1 - timeBits - workerBits
    private val maxWorkerId: Long = -1L xor (-1L shl workerBits)

    private val workerIdShift = seqBits
    private val timestampShift = seqBits + workerBits

    private val seqMask = -1L xor (-1L shl seqBits)

    init {
        require(seqBits > 0) {
            "seq bits can't be less than 0, 64 - 1 - $timeBits - $workerBits = $seqBits"
        }
    }

    fun verifyWorkerId(workerId: Long) {
        require(workerId in (0..maxWorkerId)) {
            "worker Id can't be greater than $maxWorkerId or less than 0"
        }
    }

    fun verifyTimestamp(timestamp: Long) {
        require(epochSecond <= timestamp) {
            "timeEpoch[$timeEpoch] can't be greater than timestamp[${Instant.ofEpochSecond(timestamp)}]"
        }
    }

    fun get(seconds: Long, workerId: Long, sequence: Long): Long {
        return combine(delta(seconds), workerId, sequence)
    }

    fun combine(deltaSeconds: Long, workerId: Long, sequence: Long): Long {
        return deltaSeconds shl timestampShift or (workerId shl workerIdShift) or sequence
    }

    fun nextSequence(curr: Long, step: Int = 1) = (curr + step) and seqMask

    fun delta(seconds: Long) = seconds - epochSecond

    private fun epoch(deltaSeconds: Long) = deltaSeconds + epochSecond

    fun getSequence(id: Long) = id and seqMask

    fun getDeltaSeconds(id: Long) = (id shr timestampShift)

    fun getWorkerId(id: Long) = (id shr workerIdShift) and maxWorkerId

    fun spec(id: Long): IdSpec {
        val timestamp = epoch(getDeltaSeconds(id))
        val workId = getWorkerId(id)
        val sequence = getSequence(id)
        return IdSpec(
            id = id,
            sequence = sequence,
            workId = workId,
            timestamp = timestamp
        )
    }

    fun spec(): SnowflakeSpec {
        val maxDeltaSeconds: Long = -1L xor (-1L shl timeBits)
        return SnowflakeSpec(
            timeBits = timeBits,
            timeEpoch = timeEpoch,
            epochSecond = epochSecond,
            validityPeriod = Instant.ofEpochSecond(epoch(maxDeltaSeconds)).toString(),
            workerBits = workerBits,
            seqBits = seqBits,
            totalWorkerNumber = maxWorkerId + 1,
            sequenceNumberPerSecond = seqMask + 1
        )
    }

    data class IdSpec(
        val id: Long,
        val sequence: Long,
        val workId: Long,
        val timestamp: Long,
        val time: Instant = Instant.ofEpochSecond(timestamp)
    )

    data class SnowflakeSpec(
        val timeBits: Int,
        val timeEpoch: String,
        val epochSecond: Long,
        val validityPeriod: String,
        val workerBits: Int,
        val seqBits: Int,
        val totalWorkerNumber: Long,
        val sequenceNumberPerSecond: Long
    )
}
