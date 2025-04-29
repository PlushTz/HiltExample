package com.example.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Desc:结构化并发
 * @author lijt
 * Created on 2025/4/28
 * Email: lijt@eetrust.com
 */
class Concurrency {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * 并发案例01
     */
    @Test
    fun concurrency() = runBlocking {
        val deferred = async {
            loadData()
        }

        println("waiting...") // 1.

        println(deferred.await()) // 4.
    }

    private suspend fun loadData(): Int {
        println("loading...") // 2.
        delay(1000)
        println("loaded!") // 3.
        return 42
    }

    @Test
    fun concurrency02() = runBlocking {
        val deferreds = (1..3).map {
            async {
                delay(1000L * it)
                println("loading $it")
                it
            }
        }
        val sum = deferreds.awaitAll()
            .sum()
        println(sum)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `结构化并发01`() = runBlocking<Unit> {
        val scope = CoroutineScope(EmptyCoroutineContext)
        var innerJob: Job? = null
        val job = scope.launch {
            innerJob = launch {
                delay(1000)
            }
        }

        val children = job.children
        println("children count: ${children.count()}") // 1
        println("innerJob === children.first(): ${innerJob === children.first()}") // true
        println("innerJob?.parent === job: ${innerJob?.parent === job}") // true

        job.join()
    }

    @Test
    fun `结构化并发02`() = runBlocking<Unit> {
        val scope = CoroutineScope(EmptyCoroutineContext)
        // 父子协程是并行运行的，但是因为子协程等待 100，父协程需要等待
        // 子协程运行完毕后再结束自己，所以在 job.join() 的位置，runBlocking
        // 协程会等待 job 大概 100ms 的时间
        val job = scope.launch {
            launch {
                println("打印delay之前")
                delay(1000)
                println("打印delay之后")
            }
        }
        println("scope")
        val startTime = System.currentTimeMillis()
        // 这里必须 join 一下，因为 runBlocking 开启的协程不是 job 的父协程
        // （job 的父协程是 scope 内包含的 Job），它不会等待 job 运行完再结束
        job.join()
        println("join")
        val duration = System.currentTimeMillis() - startTime
        println("Duration: $duration") // 输出在 100ms 左右
    }
}