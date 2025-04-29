package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * Desc:Flow
 * @author lijt
 * Created on 2025/4/28
 * Email: lijt@eetrust.com
 */
class FlowTest {
    @Test
    fun flowTest() = runBlocking {
        println("---------------------- flow test start ----------------------")
        simple().forEach { value -> println(value) }
        println("---------------------- flow test end ----------------------")
    }

    private fun simple() = listOf(1, 2, 3)

    @Test
    fun sequenceTest() {
        simpleSequence().forEach { value -> println(value) }
    }

    private fun simpleSequence() = sequence {
        for (i in 1..3) {
            Thread.sleep(1000)
            yield(i)
        }
    }

    @Test
    fun flowsAreCold() = runBlocking {
        println("Calling simple function...")
        val flow = simpleFlowsAreCold()
        println("Calling collect...")
        flow.collect { value -> println(value) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }

    private fun simpleFlowsAreCold() = flow {
        println("Flow started")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    @Test
    fun flowCancellationBasicsTest() = runBlocking<Unit> {
        withTimeoutOrNull(250) {
            flowCancellationBasics().collect { value -> println(value) }
        }
        println("Done")
    }

    private fun flowCancellationBasics(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            println("Emitting $i")
            emit(i)
        }
    }

    /**
     * flow操作符不允许使用withContext来指定上下文
     */
    @Test
    fun example() = runBlocking {
        testExample().collect { value -> println(value) }
    }

    private fun testExample() = flow<Int> {

        // flow不允许使用
        /*withContext(Dispatchers.IO) {
            for (i in 1..3) {
                Thread.sleep(1000)
                emit(i)
            }
        }*/

        for (i in 1..3) {
            Thread.sleep(1000)
            emit(i)
        }
    }.flowOn(Dispatchers.IO)

    @Test
    fun bufferTest() = runBlocking {
        val flow = flow {
            for (i in 1..3) {
                delay(100)
                emit(i)
            }
        }
        val time = measureTimeMillis {

            flow.conflate()
                .collect { value ->
                    println(value)
                }

            flow.buffer(capacity = 1, onBufferOverflow = BufferOverflow.SUSPEND)
                .collect { value ->
                    delay(300)
                    println(value)
                }
        }

        println("Collected in $time ms")
    }


}