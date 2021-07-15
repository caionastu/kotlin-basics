package coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = runBlocking {
//    coroutineWithFunction()
//    coroutineWithClass()
//    coroutineWithJob()
    coroutineWithClassJob()
}

private suspend fun coroutineWithClassJob() = coroutineScope {
    val coroutine = CoroutineClass(20000L)
    launch { coroutine.startJob() }
    println("main: Waiting 10 sec")
    delay(10000L)
    println("main: Stopping Job")
    coroutine.stopJob()
    println("main: Starting new Job")
    launch { coroutine.startJob() }
    println("main: Waiting 4 sec")
    delay(4000L)
    println("main: Stopping Job")
    coroutine.stopJob()
    println()
}

private suspend fun coroutineWithJob() = coroutineScope {
    val coroutine = CoroutineClass(20000L)
    val job = launch { coroutine.startWhile() }
    delay(1000L)
    job.cancelAndJoin()
}

private suspend fun coroutineWithFunction() = coroutineScope {
    startCoroutine()
    println()
    startCoroutineAsync()
    println()
}

suspend fun startCoroutine() = coroutineScope {
    val time = measureTimeMillis {
        coroutine(2000L)
        coroutine(1000L)
        coroutine(800L)
        coroutine(3000L)
        coroutine(4000L)
    }
    println("Completed in $time ms")
}

suspend fun startCoroutineAsync() = coroutineScope {
    val time = measureTimeMillis {
        val one = async { coroutine(2000L) }
        val two = async { coroutine(5000L) }
        val three = async { coroutine(500L) }
        val four = async { coroutine(3000L) }
        val five = async { coroutine(800L) }

        one.await()
        two.await()
        three.await()
        four.await()
        five.await()
    }
    println("Completed in $time ms")
}

suspend fun coroutine(time: Long) {
    delay(time)
    println("Coroutine finished with time ${time}.")
}

private suspend fun coroutineWithClass() = coroutineScope {
    println("Coroutine Classes")
    val coroutine = CoroutineClass(1000L)
    val coroutine2 = CoroutineClass(3000L)
    val coroutine3 = CoroutineClass(500L)
    val coroutine4 = CoroutineClass(200L)
    val coroutine5 = CoroutineClass(1000L)

    var time = measureTimeMillis {
        coroutine.start()
        coroutine2.start()
        coroutine3.start()
        coroutine4.start()
        coroutine5.start()
    }
    println("Completed in $time ms")
    println()

    time = measureTimeMillis {
        val one = async { coroutine.start() }
        val two = async { coroutine2.start() }
        val three = async { coroutine3.start() }
        val four = async { coroutine4.start() }
        val five = async { coroutine5.start() }

        one.await()
        three.await()
        two.cancelAndJoin()
        four.await()
        five.await()
    }
    println("Completed in $time ms")
}

