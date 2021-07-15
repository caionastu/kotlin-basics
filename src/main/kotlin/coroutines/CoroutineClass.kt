package coroutines

import kotlinx.coroutines.*

class CoroutineClass(private val time: Long) {
    private var job: Job? = null

    suspend fun start() = coroutineScope {
        coroutine(time)
    }

    suspend fun startWhile() = coroutineScope {
        var second = 0;
        while (isActive || second >= time) {
            delay(1000L)
            println("coroutine: ${++second} seconds has passed.")
        }
    }

    suspend fun startJob() = coroutineScope {
        if (job == null || !job!!.isActive) {
            println("job: Starting new Job")
            job = launch { startWhile() }
        }
    }

    suspend fun stopJob() = coroutineScope {
        job!!.cancelAndJoin()
    }
}