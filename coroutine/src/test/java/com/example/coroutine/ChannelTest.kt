package com.example.coroutine

import kotlinx.coroutines.channels.Channel
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
}