package com.example.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Desc:Channel
 * @author lijt
 * Created on 2025/4/28
 * Email: lijt@eetrust.com
 */
class ChannelTest {
    @Test
    fun exampleChannel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5) {
                channel.send(x)
            }
        }

        repeat(5) {
            println(channel.receive())
        }

        println("Done!")
    }

    @Test
    fun closeChannel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5) {
                channel.send(x * x)
            }
            channel.close()
            delay(500)
            channel.send(123)
        }

        for (y in channel) {
            println(y)
        }

        println("Done!")
    }

    @Test
    fun buildChannelProducer() = runBlocking {
        val squares = produceSquares()
        squares.consumeEach {
            println(it)
        }
        println("Done!")
    }

    fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
        println(this.coroutineContext)
        for (x in 1..5) send(x * x)
    }

    @Test
    fun testChannel() = runBlocking {
//        val numbers = produceNumbers()
//        val square = square(numbers)
//        repeat(5) {
//            println("${this.coroutineContext} = ${square.receive()}")
//        }
//        println("Done!")
        val numbers = numberFrom(2)
        repeat(10) {
            val prime = numbers.receive()
            println(prime)
            val filter = filter(numbers, prime)
            println("filter = ${filter.receive()}")
        }
        coroutineContext.cancelChildren()
    }

    fun CoroutineScope.produceNumbers() = produce {
        println("produceNumbers = ${this.coroutineContext}")
        var x = 1
        while (true) send(x++)
    }

    fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
        println("square = ${this.coroutineContext}")
        for (x in numbers) send(x * x)
    }

    fun CoroutineScope.numberFrom(start: Int) = produce {
        var x = start
        while (true) send(x++)
    }

    fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce {
        for (x in numbers) if (x % prime != 0) send(x)
    }
}